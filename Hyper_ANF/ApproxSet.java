//author: Martin Aumüller and Sarah Amick, ITU

public class ApproxSet {

    private final int m = 1024;
    private byte[] M = new byte[m];

    public void add(Object x) {
        int xh = x.hashCode();
        if (xh != 0) {
            int j = ((xh * 0xbc164501) & 0x7fe00000) >> 21;
            byte val = (byte) (Integer.numberOfLeadingZeros(LinearHash.hash(xh)) + 1);
            if (val > M[j]) {
                M[j] = val;
            }
        }
    }

    public void addSet(ApproxSet A){
      byte[] temp = new byte[m];
      for(int i = 0; i < m; i++){
        if(this.M[i] < A.getElementAtIndex(i)) this.M[i] = A.getElementAtIndex(i);
      }
    }

    public int estimateSize() {
        double z = 0.0;
        int v = 0;
        for (int i = 0; i < m; i++) {
            z += Math.pow(2.0, (double) -M[i]);
            if (M[i] == 0) {
                v += 1;
            }
        }
        z = 1 / z;

        double e = m * m * z * 0.7213 / ( 1 + 1.079 / m);
        if ((e < 2.5 * m) && (v > 0)) {
            e = m * Math.log(m / (double) v);
        }
        return (int) e;
    }

    public byte getElementAtIndex(int index){
      return M[index];
    }
}

class LinearHash {

    static int[] a = {0x21ae4036, 0x32435171, 0xac3338cf, 0xea97b40c, 0x0e504b22, 0x9ff9a4ef, 0x111d014d, 0x934f3787, 0x6cd079bf, 0x69db5c31, 0xdf3c28ed, 0x40daf2ad, 0x82a5891c, 0x4659c7b0, 0x73dc0ca8, 0xdad3aca2, 0x00c74c7e, 0x9a2521e2, 0xf38eb6aa, 0x64711ab6, 0x5823150a, 0xd13a3a9a, 0x30a5aa04, 0x0fb9a1da, 0xef785119, 0xc9f0b067, 0x1e7dde42, 0xdda4a7b2, 0x1a1c2640, 0x297c0633, 0x744edb48, 0x19adce93};

    public static int hash(int x) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += (Long.bitCount(x & a[i]) & 1);
        }
        return res;
    }
}
