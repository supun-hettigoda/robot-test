
package com.au.robotsimulation.grid.test;

import org.junit.Assert;
import org.junit.Test;

import com.au.robotsimulation.grid.FacingDirection;

/**
 * This class contains unit tests to cover the {@link FacingDirection} methods.
 */
public class FacingDirectionTest
{
    @Test
    public void test_toLeft() throws Exception
    {
        Assert.assertEquals(FacingDirection.NORTH.toLeft(), FacingDirection.WEST);
        Assert.assertEquals(FacingDirection.WEST.toLeft(), FacingDirection.SOUTH);
        Assert.assertEquals(FacingDirection.SOUTH.toLeft(), FacingDirection.EAST);
        Assert.assertEquals(FacingDirection.EAST.toLeft(), FacingDirection.NORTH);
    }

    @Test
    public void test_toRight() throws Exception
    {
        Assert.assertEquals(FacingDirection.NORTH.toRight(), FacingDirection.EAST);
        Assert.assertEquals(FacingDirection.EAST.toRight(), FacingDirection.SOUTH);
        Assert.assertEquals(FacingDirection.SOUTH.toRight(), FacingDirection.WEST);
        Assert.assertEquals(FacingDirection.WEST.toRight(), FacingDirection.NORTH);
    }

    @Test
    public void test_indexOf()
    {
        Assert.assertEquals(FacingDirection.NORTH, FacingDirection.of(0));
        Assert.assertEquals(FacingDirection.EAST, FacingDirection.of(1));
        Assert.assertEquals(FacingDirection.SOUTH, FacingDirection.of(2));
        Assert.assertEquals(FacingDirection.WEST, FacingDirection.of(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invalidIndex()
    {
        FacingDirection.of(7);
    }
}
