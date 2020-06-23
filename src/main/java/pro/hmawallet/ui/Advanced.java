package pro.hmawallet.ui;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.HbarUnit;
import com.hedera.hashgraph.sdk.HederaNetworkException;
import com.hedera.hashgraph.sdk.HederaStatusException;
import com.hedera.hashgraph.sdk.TransactionId;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.hedera.hashgraph.sdk.TransactionRecord;
import com.hedera.hashgraph.sdk.account.AccountCreateTransaction;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.account.AccountInfo;
import com.hedera.hashgraph.sdk.account.AccountInfoQuery;
import com.hedera.hashgraph.sdk.account.AccountUpdateTransaction;
import com.hedera.hashgraph.sdk.account.CryptoTransferTransaction;
import com.hedera.hashgraph.sdk.crypto.Mnemonic;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PrivateKey;

import com.hedera.sdk.bip39.Mnemonica;
import com.hedera.sdk.bip39.MnemonicException.MnemonicChecksumException;
import com.hedera.sdk.bip39.MnemonicException.MnemonicLengthException;
import com.hedera.sdk.bip39.MnemonicException.MnemonicWordException;
import com.hedera.sdk.keygen.CryptoUtils;
import com.hedera.sdk.keygen.EDKeyChain;
import com.hedera.sdk.keygen.EDKeyPair;
import com.hedera.sdk.keygen.KeyPair;
import com.hedera.sdk.keygen.Reference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.Event;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Advanced extends javax.swing.JFrame {

    Client client;

    public Advanced() {
        initComponents();
        client = AppMain.getHederaClient();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        TF_addacc_aid1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        BT_addacc_tobase1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TP_showkeys1 = new javax.swing.JTextPane();
        TF_addacc_private = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TP_showkeys = new javax.swing.JTextPane();
        BT_showkey_ = new javax.swing.JButton();
        TF_addacc_words = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        BT_create_tobase = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TP_create_result = new javax.swing.JTextPane();
        BT_generate = new javax.swing.JButton();
        BT_create_getid = new javax.swing.JButton();
        TF_create_words = new javax.swing.JTextField();
        TF_create_aid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TF_create_balance = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        BT_update_ = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        TF_update_curent = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TP_update = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        TF_asender = new javax.swing.JTextField();
        TF_apk = new javax.swing.JTextField();
        TF_arecipient = new javax.swing.JTextField();
        TF_aamount = new javax.swing.JTextField();
        TF_amemo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BT_atransfer = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TP_Info = new javax.swing.JTextPane();
        BT_getinfo_ = new javax.swing.JButton();
        TF_info = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TP_about = new javax.swing.JTextPane();
        BT_thanks = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HMAW - advanced features");
        setMinimumSize(new java.awt.Dimension(900, 300));
        setResizable(false);

        TF_addacc_aid1.setText("0.0.");

        jLabel14.setText("Account_Id");

        jLabel15.setText("Private key");

        BT_addacc_tobase1.setText("Add to base");
        BT_addacc_tobase1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addacc_tobase1ActionPerformed(evt);
            }
        });

        TP_showkeys1.setText("Attation: Before adding an entry to the database, it is recommended to compare:\n1. Public key linked to \"Account_Id\" in Hedera network. How to get? From network explorer or tab \"Get info\".\n2. The public key that you know from the recovery procedure.\nKey 1 and key 2 must be identical. Otherwise, you will view account balance, but not be able to control the account.\n\n");
        TP_showkeys1.setAutoscrolls(false);
        jScrollPane7.setViewportView(TP_showkeys1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_addacc_private)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TF_addacc_aid1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BT_addacc_tobase1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_addacc_aid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_addacc_private, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_addacc_tobase1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add account", jPanel9);

        jLabel2.setText("Recovery phrase must be 22 or 24 words");

        TP_showkeys.setEditable(false);
        TP_showkeys.setText("You will see two variants recovered key pair. Only one of them can be suitable for your account, if the following is true:\n1. Public key linked to \"AccountId\" on Hedera network. How to get? From network explorer or tab \"Get info\".\n2. The public key that you know from the recovery procedure.\nKey 1 and key 2 must be identical. Otherwise, you will request account balance, but not be able to control the account.\n");
        TP_showkeys.setAutoscrolls(false);
        jScrollPane5.setViewportView(TP_showkeys);

        BT_showkey_.setText("Show keys");
        BT_showkey_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_showkey_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(BT_showkey_, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TF_addacc_words, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TF_addacc_words, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_showkey_)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Recovery", jPanel1);

        BT_create_tobase.setText("Add to base");
        BT_create_tobase.setEnabled(false);
        BT_create_tobase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_create_tobaseActionPerformed(evt);
            }
        });

        TP_create_result.setEditable(false);
        TP_create_result.setAutoscrolls(false);
        jScrollPane1.setViewportView(TP_create_result);

        BT_generate.setText("Generate words");
        BT_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_generateActionPerformed(evt);
            }
        });

        BT_create_getid.setText("Get account_id");
        BT_create_getid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_create_getidActionPerformed(evt);
            }
        });

        TF_create_words.setEditable(false);

        TF_create_aid.setEditable(false);

        jLabel6.setText("Accoun_Id");

        jLabel12.setText("Recovery words");

        jLabel13.setText("Init balance, Hbar");

        TF_create_balance.setText("2.0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGap(845, 845, 845))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TF_create_words, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TF_create_aid)
                                .addComponent(BT_generate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(BT_create_getid))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel13)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TF_create_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BT_create_tobase)))))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_create_words, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_create_balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(TF_create_aid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BT_generate)
                    .addComponent(BT_create_getid)
                    .addComponent(BT_create_tobase))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Create account", jPanel2);

        BT_update_.setText("Execute");
        BT_update_.setEnabled(false);
        BT_update_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_update_ActionPerformed(evt);
            }
        });

        jLabel3.setText("Current public key");

        TF_update_curent.setEditable(false);
        TF_update_curent.setEnabled(false);

        TP_update.setEditable(false);
        TP_update.setEnabled(false);
        jScrollPane2.setViewportView(TP_update);

        jButton1.setText("Validate");
        jButton1.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_update_curent, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BT_update_, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TF_update_curent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BT_update_)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Update keys", jPanel3);

        jLabel7.setText("Sender");

        TF_asender.setText("0.0.");

        TF_arecipient.setText("0.0.");

        jLabel8.setText("Private key");

        jLabel9.setText("Recipient");

        jLabel10.setText("Amount");

        jLabel11.setText("Memo");

        BT_atransfer.setText("Transfer");
        BT_atransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_atransferActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TF_apk)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_asender, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TF_amemo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TF_aamount, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TF_arecipient, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                .addComponent(BT_atransfer)))
                        .addGap(0, 661, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_asender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_apk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_arecipient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_aamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_amemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BT_atransfer)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transfer", jPanel7);

        TP_Info.setEditable(false);
        TP_Info.setAutoscrolls(false);
        jScrollPane3.setViewportView(TP_Info);

        BT_getinfo_.setText("Get");
        BT_getinfo_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_getinfo_ActionPerformed(evt);
            }
        });

        TF_info.setText("0.0.");

        jLabel5.setText("Account_Id");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(TF_info, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BT_getinfo_, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TF_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BT_getinfo_)
                        .addGap(0, 89, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Get info", jPanel4);

        jTextPane1.setText("\n1. The first action after starting is to select a network.\n2. Insert your password and press \"Apply\" button.\n3. Press  button \"Advanced\", choose tab \"Add account\", insert account data. Press \"Add to base\".\n4. \"Set payer\", \"Get balance\", \"Check account\" are applicable to the selected lines in the accounts` table.\nAccounts data is stored in encrypted form separate for each network in the launch directory.");
        jTextPane1.setAutoscrolls(false);
        jScrollPane6.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("HowToUse", jPanel6);

        TP_about.setEditable(false);
        TP_about.setText("                               Uncommercial free product. \n                   You pay Hedera network transactions only.\n                    No restriction. Use at your persil and risk.\n               To thank and support software development to:\n                                           0.0.2724\n                 In any case I would be grateful for feedback.\n                                        \n                                         ilmac@author");
        jScrollPane4.setViewportView(TP_about);

        BT_thanks.setText("Thanks");
        BT_thanks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_thanksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(BT_thanks, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_thanks)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("About", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BT_create_tobaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_create_tobaseActionPerformed
        String aid = TF_create_aid.getText();
        AppMain.addNew(aid, Mnemonic.fromString(TF_create_words.getText()).toPrivateKey().toString());
        BT_create_tobase.setEnabled(false);
        try {
            AppMain.updateBalance(aid);
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println(evt.getActionCommand() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_BT_create_tobaseActionPerformed

    private void BT_update_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_update_ActionPerformed
//        updateAccountPublicKey(null, null, null);
    }//GEN-LAST:event_BT_update_ActionPerformed

    private void BT_thanksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_thanksActionPerformed
        AppMain.setRecipient("0.0.2724");
        dispose();
    }//GEN-LAST:event_BT_thanksActionPerformed

    private void BT_getinfo_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_getinfo_ActionPerformed
        String ac = TF_info.getText().trim();
        if (ac.length() < 5) {
            new TroubleDialog("Input error", true, "Failure! Invalid account_Id.\n"
            ).setVisible(true);
        } else {
            getAccountInfo(AccountId.fromString(ac));
        }
    }//GEN-LAST:event_BT_getinfo_ActionPerformed

    private void BT_showkey_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_showkey_ActionPerformed
        StringBuilder sb = new StringBuilder();
        String words = TF_addacc_words.getText();
        int length = words.split(" ").length;
        KeyPair pair = fromRecovery(words, 0);
        Ed25519PrivateKey oldType;
        if (pair != null) {
            oldType = Ed25519PrivateKey.fromString(pair.getPrivateKeyEncodedHex());
            sb.append("Words " + length + ". Validation ... Success.\n");
            sb.append("Private 1 = " + oldType + "\n" + "Public  1 = " + oldType.publicKey + "\n\n");
        } else {
            sb.append("Words " + length + ". Validation ... Failure.\n");
            sb.append("Private 1 = " + "\n" + "Public  1 = " + "\n\n");
        }
        Ed25519PrivateKey newtype;
        Mnemonic m2 = Mnemonic.fromString(words);
        if (m2.validate().isOk()) {
            newtype = m2.toPrivateKey();
            sb.append("Words " + length + ". Validation ... Success.\n");
            sb.append("Private 2 = " + newtype + "\n" + "Public  2 = " + newtype.publicKey);
        } else {
            sb.append("Words " + length + ". Validation ... Failure. " + m2.validate() + "\n");
            sb.append("Private 2 = " + "\n" + "Public  2 = ");
        }
        TP_showkeys.setText(sb.toString());
    }//GEN-LAST:event_BT_showkey_ActionPerformed

    private void BT_atransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_atransferActionPerformed
        String operator = TF_asender.getText().trim();
        String recipient = TF_arecipient.getText().trim();
        if (operator.length() < 5 || recipient.length() < 5) {
            new TroubleDialog("Input error", true, "Failure!\n"
                    + "Invalid accountId sender or recipient.\n"
            ).setVisible(true);
            return;
        }
        if (TF_aamount.getText().equals("0.0")) {
            new TroubleDialog("Input error", true, "Amount not set.").setVisible(true);
            return;
        }
        if (TF_amemo.getText().trim().getBytes().length > 100) {
            new TroubleDialog("Input error", true, "Memo too long.").setVisible(true);
            return;
        }
        Client hederaclient = AppMain.mainnet ? Client.forMainnet() : Client.forTestnet();
        AccountId operatorId = AccountId.fromString(operator);
        hederaclient.setOperator(operatorId, Ed25519PrivateKey.fromString(TF_apk.getText().trim()));
        AccountId recipientId = AccountId.fromString(recipient);
        Hbar amount = Hbar.from(new BigDecimal(TF_aamount.getText().trim()), HbarUnit.Hbar);
        String memo = TF_amemo.getText().trim();
        try {
            TransactionId transactionId = new CryptoTransferTransaction()
                    .addSender(operatorId, amount).addRecipient(recipientId, amount).setTransactionMemo(memo).execute(hederaclient);
            TransactionRecord record = transactionId.getRecord(hederaclient);
            System.out.println("transaction ID: " + transactionId + " transferred " + amount + "... from " + operator + " to " + recipient);
            AppMain.updateBalance(recipient);
            AppMain.updateBalance(operator);
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println(evt.getActionCommand() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_BT_atransferActionPerformed

    private void BT_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_generateActionPerformed
//        TF_create_words.setText(generate24());
        TF_create_words.setText(Mnemonic.generate().toString());
    }//GEN-LAST:event_BT_generateActionPerformed

    private void BT_create_getidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_create_getidActionPerformed
        String rw24 = TF_create_words.getText();
        Ed25519PrivateKey newKey = Mnemonic.fromString(rw24).toPrivateKey();
        StringBuilder sb = new StringBuilder();
        if (!AppMain.isPayerSet()) {
            new TroubleDialog("Input error", true, "Payer not set.").setVisible(true);
            return;
        }
        try {
            TransactionId txId = new AccountCreateTransaction()
                    .setKey(newKey.publicKey)
                    .setInitialBalance(Hbar.from(new BigDecimal(TF_create_balance.getText()), HbarUnit.Hbar))
                    .execute(client);
            TransactionReceipt receipt = txId.getReceipt(client);
            AccountId newAccountId = receipt.getAccountId();
            TF_create_aid.setText(newAccountId.toString());
            sb.append("Creating account for " + (AppMain.mainnet ? "Mainnet" : "Testnet") + "...Success! ");
            sb.append("Save recovery data. Add account to base.\n\n");
            sb.append("Account_Id = ").append(newAccountId).append("\n");
            sb.append("Pivate key = ").append(newKey).append("\n");
            sb.append("Recovery phrase = ").append(rw24).append("\n");
            TP_create_result.setText(sb.toString());
            BT_create_tobase.setEnabled(true);
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println(evt.getActionCommand() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_BT_create_getidActionPerformed

    private void BT_addacc_tobase1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_addacc_tobase1ActionPerformed
        String aid = TF_addacc_aid1.getText().trim();
        String rw = TF_addacc_private.getText().trim();
        if (aid.length() < 5) {
            new TroubleDialog("Input error", true, "Failure! Invalid account_id.\n"
            ).setVisible(true);
            return;
        }
        AppMain.addNew(aid, rw);
        TF_addacc_aid1.setText("0.0.");
        TF_addacc_private.setText("");
        try {
            AppMain.updateBalance(aid);
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println(evt.getActionCommand() + " " + ex.getMessage());
        }
    }//GEN-LAST:event_BT_addacc_tobase1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_addacc_tobase1;
    private javax.swing.JButton BT_atransfer;
    private javax.swing.JButton BT_create_getid;
    private javax.swing.JButton BT_create_tobase;
    private javax.swing.JButton BT_generate;
    private javax.swing.JButton BT_getinfo_;
    private javax.swing.JButton BT_showkey_;
    private javax.swing.JButton BT_thanks;
    private javax.swing.JButton BT_update_;
    private javax.swing.JTextField TF_aamount;
    private javax.swing.JTextField TF_addacc_aid1;
    private javax.swing.JTextField TF_addacc_private;
    private javax.swing.JTextField TF_addacc_words;
    private javax.swing.JTextField TF_amemo;
    private javax.swing.JTextField TF_apk;
    private javax.swing.JTextField TF_arecipient;
    private javax.swing.JTextField TF_asender;
    private javax.swing.JTextField TF_create_aid;
    private javax.swing.JTextField TF_create_balance;
    private javax.swing.JTextField TF_create_words;
    private javax.swing.JTextField TF_info;
    private javax.swing.JTextField TF_update_curent;
    private javax.swing.JTextPane TP_Info;
    private javax.swing.JTextPane TP_about;
    private javax.swing.JTextPane TP_create_result;
    private javax.swing.JTextPane TP_showkeys;
    private javax.swing.JTextPane TP_showkeys1;
    private javax.swing.JTextPane TP_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    private void updateAccountPublicKey(AccountId aid, String ms) {
        StringBuilder sb = new StringBuilder();
        sb.append("UpdateAccountPublicKey for: ").append(aid).append("\n");
        client.setMaxTransactionFee(800000000);
        Ed25519PrivateKey originalKey = Ed25519PrivateKey.fromString(fromRecovery(ms, 0).getPrivateKeyEncodedHex());
        AccountId accountId = null;
        try {
            TransactionId acctTransactionId = new AccountCreateTransaction()
                    .setMaxTransactionFee(1_000_000_000)
                    .setKey(originalKey.publicKey)
                    .setInitialBalance(new Hbar(1))
                    .execute(client);
            System.out.println("transaction ID: " + acctTransactionId);
            accountId = acctTransactionId.getReceipt(client).getAccountId();
            System.out.println("account = " + accountId);
//            sb.append("account = ").append(accountId).append("\n");
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println("AccountUpdate " + ex.getMessage());
        }
        // Next, we update the key
        Ed25519PrivateKey newPrivate = Ed25519PrivateKey.generate();
        System.out.println(" :: update public key of account " + accountId);
//        sb.append(" :: update public key of account ").append(accountId).append("\n");
        System.out.println("set key = " + newPrivate.publicKey);
//        sb.append("set key = ").append(newPrivate.publicKey).append("\n");
        try {
            TransactionId transactionId = new AccountUpdateTransaction()
                    .setAccountId(accountId)
                    .setKey(newPrivate.publicKey)
                    .build(client)
                    // Sign with the previous key and the new key
                    .sign(originalKey)
                    .sign(newPrivate)
                    .execute(client);
            System.out.println("transaction ID: " + transactionId);
//            sb.append("transaction ID: ").append(transactionId).append("\n");
            // (important!) wait for the transaction to complete by querying the receipt        
            transactionId.getReceipt(client);
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println("AccountUpdate " + ex.getMessage());
        }

        // Now we fetch the account information to check if the key was changed
        System.out.println(" :: getAccount and check our current key");
        sb.append(" :: getAccount and check our current key").append("\n");
        try {
            AccountInfo info = new AccountInfoQuery()
                    .setAccountId(accountId)
                    .execute(client);
            System.out.println("key = " + info.key);
            sb.append("key = ").append(info.key).append("\n");
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println("AccountUpdate " + ex.getMessage());
        }
    }

    private void getAccountInfo(AccountId aid) {
        StringBuilder sb = new StringBuilder();
        sb.append("AccountInfoQuery for ");
        if (!AppMain.isPayerSet()) {
            new TroubleDialog("Input error", true, "Payer not set.").setVisible(true);
            return;
        }
        try {
            AccountInfo info = new AccountInfoQuery().setAccountId(aid).execute(client);
            sb.append("accountId; " + info.accountId).append("\n");
            sb.append("balance: " + Hbar.fromTinybar(info.balance) + " -> " + Hbar.fromTinybar(info.balance).as(HbarUnit.Hbar) + " HBar").append("\n");
            sb.append("contractAccountId: " + info.contractAccountId).append("\n");
            sb.append("PublicKey: " + info.key).append("\n");
            sb.append("PublicKeyProto: " + info.key.toKeyProto()).append("\n");
            sb.append("autoRenewPeriod: " + info.autoRenewPeriod).append("\n");
            sb.append("expirationTime: " + info.expirationTime).append("\n");
            sb.append("generateReceiveRecordThreshold: " + info.generateReceiveRecordThreshold).append("\n");
            sb.append("generateSendRecordThreshold: " + info.generateSendRecordThreshold).append("\n");
            sb.append("isDeleted: " + info.isDeleted).append("\n");
            sb.append("isReceiverSignatureRequired: " + info.isReceiverSignatureRequired).append("\n");
            sb.append("proxyAccountId: " + info.proxyAccountId).append("\n");
            sb.append("proxyReceived: " + info.proxyReceived).append("\n");
            TP_Info.setText(sb.toString());
        } catch (HederaStatusException | HederaNetworkException ex) {
            System.out.println("getAccountInfo " + ex.getMessage());
        }
    }

    private String generate24() {
        Reference referenceSeed = new Reference(CryptoUtils.getSecureRandomData(32));
        List<String> mnemonicList = null;
        try {
            mnemonicList = new Mnemonica().toMnemonic(referenceSeed.toBytes());
        } catch (MnemonicLengthException ex) {
        }
        return String.join(" ", mnemonicList);
    }

    private List<String> toList(String wordList) {
        List<String> allWords = Collections.synchronizedList(new ArrayList<>());
        Matcher m = Pattern.compile("[a-zA-Z]+").matcher(wordList.toLowerCase());
        while (m.find()) {
            allWords.add(m.group());
        }
        return allWords;
    }

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

    private String showKeys(KeyPair keyPair) {
        StringBuilder sb = new StringBuilder();
        if (keyPair != null) {
            sb.append("PrivateHex : " + keyPair.getPrivateKeyHex() + "\n");
            sb.append("EncodedHex : " + keyPair.getPrivateKeyEncodedHex() + "\n\n");
            sb.append("PublicHex  : " + keyPair.getPublicKeyHex() + "\n");
            sb.append("EncodedHex : " + keyPair.getPublicKeyEncodedHex());
//            BT_addacc_tobase.setEnabled(true);
        } else {
            sb.append("Input error. Invalid recovery word.");
//            BT_addacc_tobase.setEnabled(false);
        }
        return sb.toString();
    }

    private void copyToClipboard(String copyThis) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(copyThis);
        clipboard.setContent(content);
    }

    private void handleTextSelect(Event event) {
//        if (event.getSource().getClass() == TextField.class) {
//            TextField text = (TextField) event.getSource();
//            text.selectAll();
//        } else if (event.getSource().getClass() == TextArea.class) {
//            TextArea text = (TextArea) event.getSource();
//            text.selectAll();
//        }
    }
}
