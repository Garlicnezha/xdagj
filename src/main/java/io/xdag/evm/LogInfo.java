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
package io.xdag.evm;

import org.apache.tuweni.bytes.Bytes;

import java.util.List;

/**
 * Represents a Log emitted by a smart contract.
 */
public class LogInfo {

    private Bytes address;
    private List<DataWord> topics;
    private Bytes data;

    public LogInfo(Bytes address, List<DataWord> topics, Bytes data) {
        this.address = address;
        this.topics = topics;
        this.data = data;
    }

    public Bytes getAddress() {
        return address;
    }

    public List<DataWord> getTopics() {
        return topics;
    }

    public Bytes getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (DataWord topic : topics) {
            sb.append(topic.toString()).append(" ");
        }
        sb.append("]");

        return "LogInfo{" +
                "address=" + address.toHexString() +
                ", topics=" + sb +
                ", data=" + data.toHexString() +
                '}';
    }
}

