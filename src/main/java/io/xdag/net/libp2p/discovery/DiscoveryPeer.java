/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020-2030 The XdagJ Developers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.xdag.net.libp2p.discovery;

import com.google.common.base.MoreObjects;
import java.net.InetSocketAddress;
import java.util.Objects;
import lombok.Getter;
import org.apache.tuweni.bytes.Bytes;

/**
 * @author wawa
 */
@Getter
public class DiscoveryPeer {

    private final Bytes publicKey;
    private final InetSocketAddress nodeAddress;

    public DiscoveryPeer(Bytes publicKey, InetSocketAddress nodeAddress) {
        this.publicKey = publicKey;
        this.nodeAddress = nodeAddress;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscoveryPeer that = (DiscoveryPeer) o;
        return Objects.equals(publicKey, that.publicKey) && Objects.equals(nodeAddress, that.nodeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicKey, nodeAddress);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("publicKey", publicKey)
                .add("nodeAddress", nodeAddress)
                .toString();
    }
}