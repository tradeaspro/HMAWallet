package pro.hmawallet.ui;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.HbarUnit;
import com.hedera.hashgraph.sdk.HederaNetworkException;
import com.hedera.hashgraph.sdk.HederaStatusException;
import com.hedera.hashgraph.sdk.TransactionId;
import com.hedera.hashgraph.sdk.TransactionRecord;
import com.hedera.hashgraph.sdk.account.AccountBalanceQuery;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.account.AccountInfoQuery;
import com.hedera.hashgraph.sdk.account.CryptoTransferTransaction;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PrivateKey;

import com.hedera.sdk.bip39.MnemonicException.MnemonicChecksumException;
import com.hedera.sdk.bip39.MnemonicException.MnemonicLengthException;
import com.hedera.sdk.bip39.MnemonicException.MnemonicWordException;
import com.hedera.sdk.bip39.Mnemonica;

import com.hedera.sdk.keygen.CryptoUtils;
import com.hedera.sdk.keygen.EDKeyChain;
import com.hedera.sdk.keygen.EDKeyPair;
import com.hedera.sdk.keygen.KeyPair;
import com.hedera.sdk.keygen.Reference;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

public class AppMain extends javax.swing.JFrame {

    static DefaultTableModel tm;
    static List<String> accountsList = new ArrayList<>();
    static File mainbase = new File("main");
    static File testbase = new File("test");
    static boolean onpause;
    static AccountId OPERATOR_ID = AccountId.fromString("0.0.2877");
    static Ed25519PrivateKey OPERATOR_KEY = Ed25519PrivateKey.generate();
    static boolean mainnet = false;
    private Timer timer = null;
    private OkHttpClient htclient = null;
    private Request request = null;
    static Client client;

    public AppMain() {
        initComponents();
        tm = (DefaultTableModel) Table.getModel();
//        RB_mainnet.setEnabled(false);
        if (RB_mainnet.isSelected()) {
            mainnet = true;
        }
        String net = mainnet ? "Mainnet" : "Testnet";
        settitle("Hedera Multi Account Wallet - " + net);
        System.out.println("Selected: " + net);
//        System.out.println(net + " accounts recorded: " + accountsList.size());

        htclient = new OkHttpClient();//.Builder().build();
        request = new Request.Builder().url("https://api.binance.com/api/v3/ticker/price?symbol=HBARUSDT").build();
        setPrice();
        timer = new javax.swing.Timer(2000, (java.awt.event.ActionEvent e) -> {
            setPrice();
        });
//        timer.start();
        CopyPastSupport support = new CopyPastSupport();
        support.setPopup(TF_memo);
    }

    final void settitle(String s) {
        setTitle(s);
    }

    static Client getHederaClient() {
        return client;
    }

    static boolean isPayerSet() {
        return TF_payer.getText().length() > 5;
    }

    static void setRecipient(String string) {
        TF_recipient.setText(string);
    }

    static void addNew(String id, String sw) {
        onpause = true;
        if (TB_apply.isSelected()) {
            String ens = new Cryptor(PF.getPassword()).encrypt(id + ":" + sw);
            accountsList.add(ens);
            store(mainnet ? mainbase : testbase, true, ens + "\n");
            int i = accountsList.size() - 1;
            tm.addRow(new Object[i]);
            tm.setValueAt(id, i, tm.findColumn("Account_Id"));
            tm.setValueAt(Hbar.ZERO.as(HbarUnit.Hbar), i, tm.findColumn("HBAR"));
            tm.setValueAt(0d, i, tm.findColumn("USD"));
            tm.setValueAt(Hbar.ZERO.as(HbarUnit.Tinybar), i, tm.findColumn("Tinybar"));
            System.out.println("Add to base: " + id);
        }
        if (!TB_apply.isSelected()) {
            new TroubleDialog("Must before...", true, "Set password and press \"Apply\"\n").setVisible(true);
        }
        onpause = false;
    }

    private void setPrice() {
        try {
            Response response = htclient.newCall(request).execute();
            ResponseBody body = response.body();
            if (body != null) {
                String message = body.string();
                if (message.startsWith("{")) {
                    double price = new JSONObject(message).getDouble("price");
//                System.out.println(price);
                    TF_price.setText(Double.toString(round(price, 6)));
                    if (!onpause) {
                        for (int i = 0; i < tm.getRowCount(); i++) {
                            BigDecimal bd = (BigDecimal) tm.getValueAt(i, tm.findColumn("HBAR"));
                            double fiat = round(bd.doubleValue() * price, 6);
                            tm.setValueAt(fiat, i, tm.findColumn("USD"));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setPrice(int row) {
        BigDecimal bd = (BigDecimal) tm.getValueAt(row, tm.findColumn("HBAR"));
        double price = Double.valueOf(TF_price.getText());
        double fiat = round(bd.doubleValue() * price, 4);
        tm.setValueAt(fiat, row, tm.findColumn("USD"));
    }

    static AccountId getOperId() {
        return OPERATOR_ID;
    }

    static Ed25519PrivateKey getOperKey() {
        return OPERATOR_KEY;
    }

    private void setPayer(int i) {
        String[] rec = new Cryptor(PF.getPassword()).decrypt(accountsList.get(i)).split(":");
        if (!"invalid".equals(rec[0])) {
            OPERATOR_ID = AccountId.fromString(rec[0]);
            OPERATOR_KEY = Ed25519PrivateKey.fromString(rec[1]);
            System.out.println("Payer now is: " + OPERATOR_ID);
//            System.out.println("Key: " + OPERATOR_KEY);
            client.setOperator(OPERATOR_ID, OPERATOR_KEY);
            TF_payer.setText(OPERATOR_ID.toString());
        }
    }

    private Hbar getBalance(AccountId id) throws HederaStatusException {
        return new AccountBalanceQuery().setAccountId(id).execute(client);
    }

    static int getRow(String accountId) {
        int colunm = tm.findColumn("Account_Id");
        for (int i = 0; i < tm.getRowCount(); i++) {
            if (tm.getValueAt(i, colunm).equals(accountId)) {
                return i;
            }
        }
        return -1;
    }

    static void updateBalance(String accountId) throws HederaStatusException {
        int i = getRow(accountId);
        if (i >= 0) {
            Hbar balance = new AccountBalanceQuery().setAccountId((AccountId.fromString(accountId))).execute(client);
            tm.setValueAt(balance.as(HbarUnit.Hbar), i, tm.findColumn("HBAR"));
            tm.setValueAt(balance.as(HbarUnit.Tinybar), i, tm.findColumn("Tinybar"));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        PF = new javax.swing.JPasswordField();
        TB_apply = new javax.swing.JToggleButton();
        BT_advanced = new javax.swing.JButton();
        BT_balance_ = new javax.swing.JButton();
        BT_operset_ = new javax.swing.JButton();
        RB_mainnet = new javax.swing.JRadioButton();
        TF_price = new javax.swing.JTextField();
        BT_check = new javax.swing.JButton();
        TB_excrate = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        TF_payer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TF_recipient = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TF_memo = new javax.swing.JTextField();
        BT_trans_ = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        TF_amount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hedera Multi Account Wallet");
        setMinimumSize(new java.awt.Dimension(940, 320));
        setSize(new java.awt.Dimension(940, 320));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        PF.setText("passwordpas");
        PF.setNextFocusableComponent(TB_apply);

        TB_apply.setText("Apply");
        TB_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TB_applyActionPerformed(evt);
            }
        });

        BT_advanced.setText("Advanced");
        BT_advanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_advancedActionPerformed(evt);
            }
        });

        BT_balance_.setText("Get balance");
        BT_balance_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_balance_ActionPerformed(evt);
            }
        });

        BT_operset_.setText("Set payer");
        BT_operset_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_operset_ActionPerformed(evt);
            }
        });

        RB_mainnet.setSelected(true);
        RB_mainnet.setText("Mainnet");
        RB_mainnet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RB_mainnetItemStateChanged(evt);
            }
        });

        BT_check.setText("Check account");
        BT_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_checkActionPerformed(evt);
            }
        });

        TB_excrate.setText("Price");
        TB_excrate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TB_excrateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BT_advanced)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_operset_)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_balance_)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_check)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TB_excrate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TF_price, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RB_mainnet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PF, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TB_apply)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RB_mainnet)
                        .addComponent(TF_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TB_excrate))
                    .addComponent(TB_apply)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BT_advanced)
                        .addComponent(BT_operset_)
                        .addComponent(BT_balance_)
                        .addComponent(BT_check)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account_Id", "HBAR", "USD", "Tinybar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        TF_payer.setEditable(false);
        TF_payer.setText("0.0.");

        jLabel2.setText("Payer:");

        jLabel1.setText("Recipient:");

        TF_recipient.setText("0.0.");

        jLabel3.setText("Memo:");

        TF_memo.setText("Thanks");

        BT_trans_.setText("Transfer");
        BT_trans_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_trans_ActionPerformed(evt);
            }
        });

        jLabel4.setText("HBAR:");

        TF_amount.setText("0.0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TF_payer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TF_recipient, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TF_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TF_memo)
                .addGap(34, 34, 34)
                .addComponent(BT_trans_)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_payer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(TF_recipient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TF_memo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_trans_)
                    .addComponent(jLabel4)
                    .addComponent(TF_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TB_applyActionPerformed
        if (!TB_apply.isSelected()) {
            int rc = tm.getRowCount();
            for (int i = 0; i < rc; i++) {
                tm.removeRow(0);
            }
            TF_payer.setText("0.0.");
            accountsList.clear();
            PF.setEnabled(true);
            RB_mainnet.setEnabled(true);
        }
        if (TB_apply.isSelected()) {
            File base = mainnet ? mainbase : testbase;
            if (base.exists()) {
                Scanner scan;
                try {
                    scan = new Scanner(base);
                    while (scan.hasNextLine()) {
                        accountsList.add(scan.nextLine().trim());
                    }
                    scan.close();
                } catch (FileNotFoundException ex) {
                }
            }
            client = mainnet ? Client.forMainnet() : Client.forTestnet();
            client.setOperator(OPERATOR_ID, OPERATOR_KEY);
            if (Table.getRowCount() == 0) {
                for (int i = 0; i < accountsList.size(); i++) {
                    String aid = new Cryptor(PF.getPassword()).decrypt(accountsList.get(i)).split(":")[0];
                    if ("invalid".equals(aid)) {
                        new TroubleDialog("Decrypt error...", true, "Record " + i + " does not be decrypted.").setVisible(true);
                    }
                    tm.addRow(new Object[tm.getColumnCount() - 1]);
                    tm.setValueAt(aid, i, tm.findColumn("Account_Id"));
                    tm.setValueAt(Hbar.ZERO.as(HbarUnit.Hbar), i, tm.findColumn("HBAR"));
                    tm.setValueAt(0d, i, tm.findColumn("USD"));
                    tm.setValueAt(Hbar.ZERO.as(HbarUnit.Tinybar), i, tm.findColumn("Tinybar"));
                }
            }
            PF.setEnabled(false);
            RB_mainnet.setEnabled(false);
        }
    }//GEN-LAST:event_TB_applyActionPerformed

    private void BT_advancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_advancedActionPerformed
        new Advanced().setVisible(true);
    }//GEN-LAST:event_BT_advancedActionPerformed

    private void BT_operset_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_operset_ActionPerformed
        int select = Table.getSelectedRow();
        if (select >= 0) {
            setPayer(select);
        }
    }//GEN-LAST:event_BT_operset_ActionPerformed

    private void BT_balance_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_balance_ActionPerformed
        try {
            if (tm.getRowCount() > 0) {
                for (int i = 0; i < tm.getRowCount(); i++) {
                    if (Table.isRowSelected(i)) {
                        AccountId id = AccountId.fromString(tm.getValueAt(i, tm.findColumn("Account_Id")).toString().trim());
//                        System.out.println(id);
                        Hbar balance = new AccountBalanceQuery().setAccountId(id).execute(client);
                        tm.setValueAt(balance.as(HbarUnit.Hbar), i, tm.findColumn("HBAR"));
//                        tm.setValueAt(0d, i, tm.findColumn("USD"));
                        setPrice(i);
                        tm.setValueAt(balance.as(HbarUnit.Tinybar), i, tm.findColumn("Tinybar"));
                    }
                }
            }
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println(evt.getActionCommand() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_BT_balance_ActionPerformed

    private void BT_trans_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_trans_ActionPerformed
        String recipient = TF_recipient.getText().trim();
        String operator = TF_payer.getText().trim();
        if (!OPERATOR_ID.equals(AccountId.fromString(operator))) {
            new TroubleDialog("Input error", true, "Payer not set.").setVisible(true);
            return;
        }
        if (operator.length() < 5) {
            new TroubleDialog("Input error", true, "Set payer.\n"
            ).setVisible(true);
            return;
        }
        if (recipient.length() < 5) {
            new TroubleDialog("Input error", true, "Set recipient.\n"
            ).setVisible(true);
            return;
        }
        if (TF_amount.getText().equals("0.0")) {
            new TroubleDialog("Input error", true, "Amount not set.").setVisible(true);
            return;
        }
        if (TF_memo.getText().trim().getBytes().length > 100) {
            new TroubleDialog("Input error", true, "Memo too long.").setVisible(true);
            return;
        }
        AccountId recipientId = AccountId.fromString(recipient);
        Hbar amount = Hbar.from(new BigDecimal(TF_amount.getText().trim()), HbarUnit.Hbar);
        String memo = TF_memo.getText().trim();
        try {
            TransactionId transactionId = new CryptoTransferTransaction()
                    .addSender(OPERATOR_ID, amount).addRecipient(recipientId, amount).setTransactionMemo(memo).execute(client);
            TransactionRecord record = transactionId.getRecord(client);
            System.out.println("transaction ID: " + transactionId + " transferred " + amount + "... from " + OPERATOR_ID + " to " + recipient);
            updateBalance(recipient);
            updateBalance(operator);
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println(evt.getActionCommand() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_BT_trans_ActionPerformed

    private void RB_mainnetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RB_mainnetItemStateChanged
        if (RB_mainnet.isSelected()) {
            mainnet = true;
            String net = mainnet ? "Mainnet" : "Testnet";
            setTitle("Hedera Multi Account Wallet - " + net);
            System.out.println("Selected: " + net);
            System.out.println(net + " accounts recorded: " + accountsList.size());
        }
        if (!RB_mainnet.isSelected()) {
            mainnet = false;
            String net = mainnet ? "Mainnet" : "Testnet";
            setTitle("Hedera Multi Account Wallet - " + net);
            System.out.println("Selected: " + net);
            System.out.println(net + " accounts recorded: " + accountsList.size());
        }
    }//GEN-LAST:event_RB_mainnetItemStateChanged

    private void BT_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_checkActionPerformed
        int select = Table.getSelectedRow();
        if (select >= 0) {
            AccountId checkId = AccountId.fromString(tm.getValueAt(select, tm.findColumn("Account_Id")).toString());
            String[] des = new Cryptor(PF.getPassword()).decrypt(accountsList.get(select)).split(":");
            if (des.length < 2) {
                new TroubleDialog("Check account result", true, "Failure!\n" + "Invalid record in the base.\n").setVisible(true);
                return;
            }
            if (!"invalid".equals(des[0])) {
                if (!AppMain.isPayerSet()) {
                    new TroubleDialog("Input error", true, "Payer not set.").setVisible(true);
                    return;
                }
                AccountId baseId = AccountId.fromString(Objects.requireNonNull(des[0]));
                if (!checkId.toString().equals(baseId.toString())) {
                    new TroubleDialog("Check account result", true,
                            "Verifiable account is: " + checkId + "\n" + "Recorded in the base: " + baseId + "\n").setVisible(true);
                    return;
                }
                String basePublic = Ed25519PrivateKey.fromString(des[1]).publicKey.toString();
                try {
                    String netPublic = new AccountInfoQuery().setAccountId(checkId).execute(client).key.toString();
                    System.out.println("Public from net: " + netPublic);
                    System.out.println("Public from base:" + basePublic);
                    if (basePublic.equals(netPublic)) {
                        new TroubleDialog("Check account result", true, "Success!\n"
                                + "This account is under your control.").setVisible(true);
                    }
                    if (!basePublic.equals(netPublic)) {
                        new TroubleDialog("Check account result", true, "Failure!\n"
                                + "You can not manage account: " + checkId + "\n"
                                + "The public key on the network does not match\n"
                                + "key from your base.").setVisible(true);
                    }
                    updateBalance(OPERATOR_ID.toString());
                } catch (HederaStatusException | HederaNetworkException ex) {
                    System.out.println(evt.getActionCommand() + " " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_BT_checkActionPerformed

    private void TB_excrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TB_excrateActionPerformed
        if (TB_excrate.isSelected()) {
            timer.start();
        }
        if (!TB_excrate.isSelected()) {
            timer.stop();
        }
    }//GEN-LAST:event_TB_excrateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AppMain().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_advanced;
    private javax.swing.JButton BT_balance_;
    private javax.swing.JButton BT_check;
    private javax.swing.JButton BT_operset_;
    private javax.swing.JButton BT_trans_;
    private static javax.swing.JPasswordField PF;
    private javax.swing.JRadioButton RB_mainnet;
    private static javax.swing.JToggleButton TB_apply;
    private javax.swing.JToggleButton TB_excrate;
    private static javax.swing.JTextField TF_amount;
    private javax.swing.JTextField TF_memo;
    private static javax.swing.JTextField TF_payer;
    private javax.swing.JTextField TF_price;
    private static javax.swing.JTextField TF_recipient;
    private javax.swing.JTable Table;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private KeyPair fromRecovery(String wordList, int index) {
        KeyPair keyPair = null;
        wordList = wordList.replace(" ", ",");
        String[] words = wordList.split(",");
        wordList = wordList.replaceAll(",", " ");
        List<String> allWords = Collections.synchronizedList(new ArrayList<>());
        Matcher m = Pattern.compile("[a-zA-Z]+").matcher(wordList.toLowerCase());
        while (m.find()) {
            allWords.add(m.group());
        }
        if (words.length == 22) { // text="index -1 (Default for java SDK), index 0 (Default for mobile wallet)"
            keyPair = new EDKeyChain(new Reference(wordList)).keyAtIndex(index);
        } else if (words.length == 24) {
            try {
                keyPair = new EDKeyPair(CryptoUtils.deriveKey(new Mnemonica().toEntropy(allWords), index, 32));
            } catch (MnemonicLengthException | MnemonicWordException | MnemonicChecksumException ex) {
                System.out.println("fromRecovery: " + ex.getMessage());
            }
        }
        return keyPair;
    }

    private double round(double d, int scale) {
        return new BigDecimal(d).setScale(scale, RoundingMode.HALF_DOWN).doubleValue();
    }

    static String convert(char[] password) {
        StringBuilder sb = new StringBuilder();
        for (char c : password) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static void store(File file, boolean append, String s) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, append);
            writer.write(s);
        } catch (IOException ex) {
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}

class Cryptor {

    SecretKey key;
    AlgorithmParameterSpec paramSpec;
    byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03};
    int iterationCount = 19;

    public Cryptor(char[] passPhrase) {
        try {
            KeySpec keySpec = new PBEKeySpec(passPhrase, salt, iterationCount);
            key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            paramSpec = new PBEParameterSpec(salt, iterationCount);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
        }
    }

    public String encrypt(String str) {
        byte[] bytesrc = null;
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            bytesrc = cipher.doFinal(str.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException
                | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
            System.out.println("encrypt: " + ex.getMessage());
        }
        return toHexString(bytesrc);
    }

    public String decrypt(String str) {
        byte[] bytesrc;
        try {
            byte[] hex = convertHexString(str);
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            bytesrc = cipher.doFinal(hex);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException
                | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
            System.out.println("decrypt: " + ex.getMessage());
            return "invalid";
        }
        return new String(bytesrc);
    }

    private byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    private String toHexString(byte b[]) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }
        return hexString.toString();
    }
}
