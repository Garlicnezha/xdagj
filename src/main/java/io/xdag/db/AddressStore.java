package io.xdag.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.units.bigints.UInt64;
import org.bouncycastle.util.encoders.Hex;
import org.checkerframework.checker.units.qual.A;

/**
 * @author Dcj_Cory
 * @date 2022/10/13 6:25 PM
 */
@Slf4j
public class AddressStore {
    private static final int AddressSize = 20;
    private final KVSource<byte[], byte[]> AddressSource;


    //<addressHash,balance>
    public AddressStore(KVSource<byte[], byte[]> addressSource) {
        AddressSource = addressSource;
    }

    public void init() {
        this.AddressSource.init();
    }

    public void reset() {
        this.AddressSource.reset();
    }

    public UInt64 getBalanceByAddress(byte[] Address){
        byte[] data;
        data = AddressSource.get(Address) == null ? UInt64.ZERO.toBytes().toArray() : AddressSource.get(Address);
        UInt64 balance = UInt64.fromBytes(Bytes.wrap(data));
        return balance;
    }
//    public UInt64 getBalanceByAddress(String Address){
//        byte[] data;
//        data = AddressSource.get(Hex.decode(Address)) == null ? UInt64.ZERO.toBytes().toArray() : AddressSource.get(Address);
//        UInt64 balance = UInt64.fromBytes(Bytes.wrap(data));
//        return balance;
//    }

    public void addAddress(byte[] Address){
//        if(Address.length != AddressSize){
//            log.debug("The Address type is wrong");
//            return;
//        }
        if(AddressSource.get(Address) == null){
            AddressSource.put(Address,UInt64.ZERO.toBytes().toArray());
        }
    }

    public void updateBalance(byte[] Address,UInt64 balance){
//        if(Address.length != AddressSize){
//            log.debug("The Address type is wrong");
//            return;
//        }
        byte[] data = balance.toBytes().toArray();
        AddressSource.put(Address,data);
    }
}
