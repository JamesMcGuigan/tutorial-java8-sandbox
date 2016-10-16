package com.company;

import junit.framework.*;
import com.company.LongLoop;

public class LongLoopTest extends TestCase {
    protected LongLoop looper;
    protected final long answer = 2305843005992468481L;

    protected void setUp(){
        looper = new LongLoop();
    }

    public void testLittleLong() {
        assertEquals(looper.littleLong(), answer);
    }
    public void testBigLong() {
        assertEquals((long) looper.bigLong(), answer);
    }
}
