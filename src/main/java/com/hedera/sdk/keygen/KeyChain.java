package com.hedera.sdk.keygen;

public interface KeyChain {

    KeyPair keyAtIndex(long index);
}
