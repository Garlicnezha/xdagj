package io.xdag.snapshot.core;

import static io.xdag.utils.BasicUtils.amount2xdag;
import static io.xdag.utils.BasicUtils.hash2Address;

import com.google.common.primitives.UnsignedLong;
import io.xdag.core.BlockInfo;
import io.xdag.utils.XdagTime;
import java.nio.ByteOrder;
import lombok.Data;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;

@Data
public class BalanceData {

    protected long amount;
    protected long time;
    // we dont need storage_pos
    protected long storage_pos;
    protected byte[] hash;

    protected int flags;

    public BalanceData() {

    }

    public BalanceData(UnsignedLong amount, UnsignedLong time, UnsignedLong storage_pos, Bytes32 hash) {
        this.amount = amount.longValue();
        this.time = time.longValue();
        this.storage_pos = storage_pos.longValue();
        this.hash = hash.toArray();
    }

    public static BalanceData parse(Bytes key, Bytes value) {
        if (key.size() == 32) {
            UnsignedLong amount = UnsignedLong.valueOf(value.getLong(0, ByteOrder.LITTLE_ENDIAN));
            UnsignedLong time = UnsignedLong.valueOf(value.getLong(8, ByteOrder.LITTLE_ENDIAN));
            UnsignedLong storage_pos = UnsignedLong.valueOf(value.getLong(16, ByteOrder.LITTLE_ENDIAN));
            Bytes32 hash = Bytes32.wrap(key.reverse());
            return new BalanceData(amount, time, storage_pos, hash);
        }
        return null;
    }

    public static BlockInfo transferToBlockInfo(BalanceData balanceData) {
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.setTimestamp(balanceData.getTime());
        blockInfo.setAmount(balanceData.getAmount());
        blockInfo.setHash(balanceData.getHash());
        byte[] hashLow = new byte[32];
        System.arraycopy(balanceData.getHash(), 8, hashLow, 8, 24);
        blockInfo.setHashlow(hashLow);
        blockInfo.setSnapshot(true);
        return blockInfo;
    }

    @Override
    public String toString() {
        return "BalanceData{" +
                "amount=" + amount2xdag(amount) +
                ", time=" + FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS")
                .format(XdagTime.xdagTimestampToMs(time)) +
                ", storage_pos=" + storage_pos +
                ", hash=" + (hash != null ? hash2Address(hash) : "") +
                '}';
    }
}
