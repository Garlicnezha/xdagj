package io.xdag.crypto.bip44;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class HDPathTest {

    @Test
    public void testPrimaryConstructor() {
        HDPath path = new HDPath(true, Collections.emptyList());
        assertTrue("Has private key returns false incorrectly", path.hasPrivateKey);
        assertEquals("Path not empty", path.size(), 0);
    }

    @Test
    public void testExtendVarargs() {
        HDPath basePath = new HDPath(true, Collections.emptyList());
        // Make sure we can do a depth of 5 as per BIP44, etc.
        // m / 44' / coinType' / accountIndex' / change / addressIndex
        HDPath path1 = basePath.extend(ChildNumber.ZERO_HARDENED);
        HDPath path2 = basePath.extend(ChildNumber.ZERO_HARDENED, ChildNumber.ONE_HARDENED);
        HDPath path3 = basePath.extend(ChildNumber.ZERO_HARDENED, ChildNumber.ONE_HARDENED, ChildNumber.ZERO_HARDENED);
        HDPath path4 = basePath.extend(ChildNumber.ZERO_HARDENED, ChildNumber.ONE_HARDENED, ChildNumber.ZERO_HARDENED, ChildNumber.ONE);
        HDPath path5 = basePath.extend(ChildNumber.ZERO_HARDENED, ChildNumber.ONE_HARDENED, ChildNumber.ZERO_HARDENED, ChildNumber.ONE, ChildNumber.ZERO);

        assertEquals("m/0H",  path1.toString());
        assertEquals("m/0H/1H",  path2.toString());
        assertEquals("m/0H/1H/0H",  path3.toString());
        assertEquals("m/0H/1H/0H/1",  path4.toString());
        assertEquals("m/0H/1H/0H/1/0",  path5.toString());
    }

    @Test
    public void testFormatPath() {
        Object[] tv = {
                "M/44H/0H/0H/1/1",
                ImmutableList.of(new ChildNumber(44, true), new ChildNumber(0, true), new ChildNumber(0, true),
                        new ChildNumber(1, false), new ChildNumber(1, false)),

                "M/7H/3/3/1H",
                ImmutableList.of(new ChildNumber(7, true), new ChildNumber(3, false), new ChildNumber(3, false),
                        new ChildNumber(1, true)),

                "M/1H/2H/3H",
                ImmutableList.of(new ChildNumber(1, true), new ChildNumber(2, true), new ChildNumber(3, true)),

                "M/1/2/3",
                ImmutableList.of(new ChildNumber(1, false), new ChildNumber(2, false), new ChildNumber(3, false))
        };

        for (int i = 0; i < tv.length; i += 2) {
            String expectedStrPath = (String) tv[i];
            HDPath path = HDPath.M((List<ChildNumber>) tv[i + 1]);
            String generatedStrPath = path.toString();
            assertEquals(generatedStrPath, expectedStrPath);
        }
    }

    @Test
    public void testParsePath() {
        Object[] tv = {
                "M / 44H / 0H / 0H / 1 / 1",
                ImmutableList.of(new ChildNumber(44, true), new ChildNumber(0, true), new ChildNumber(0, true),
                        new ChildNumber(1, false), new ChildNumber(1, false)),
                false,

                "M/7H/3/3/1H/",
                ImmutableList.of(new ChildNumber(7, true), new ChildNumber(3, false), new ChildNumber(3, false),
                        new ChildNumber(1, true)),
                false,

                "m/7H/3/3/1H/",
                ImmutableList.of(new ChildNumber(7, true), new ChildNumber(3, false), new ChildNumber(3, false),
                        new ChildNumber(1, true)),
                true,

                "1 H / 2 H / 3 H /",
                ImmutableList.of(new ChildNumber(1, true), new ChildNumber(2, true), new ChildNumber(3, true)),
                false,

                "1 / 2 / 3 /",
                ImmutableList.of(new ChildNumber(1, false), new ChildNumber(2, false), new ChildNumber(3, false)),
                false
        };

        for (int i = 0; i < tv.length; i += 3) {
            String strPath = (String) tv[i];
            List<ChildNumber> expectedPath = (List<ChildNumber>) tv[i + 1];
            boolean expectedHasPrivateKey = (Boolean) tv[i + 2];
            HDPath path = HDPath.parsePath(strPath);
            assertEquals(path, expectedPath);
            assertEquals(path.hasPrivateKey, expectedHasPrivateKey);
        }
    }

}
