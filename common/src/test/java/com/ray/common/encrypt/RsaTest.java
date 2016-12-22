package com.ray.common.encrypt;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by zyl on 2016/12/22.
 */
public class RsaTest {
  @Test
  public void encryptC() throws Exception {
    String LoginPwdKey="yzD2IAHWgOX5LAtEFI/5XAYGfHV1kK5dYHwFcVCBIZlzyIDBni1BN/FAi+bsXpK2bF4B7hV2HTcGMx/p54mFxTds3aCT23fmtOBg3U8pv7HT7BCYOA0otFeZBBZ9Rt6/tev6W96lSYt/0x5+AN2Zz4r+ql40NRAwTGVCSIWHntM=";
    String s = Rsa.encryptC("abc123", LoginPwdKey);
    System.out.println(s);
    assertNotEquals(s, null);
  }

}