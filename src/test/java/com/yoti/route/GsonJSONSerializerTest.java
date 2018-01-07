package com.yoti.route;

import com.yoti.domain.Coordinates;
import org.junit.Assert;
import org.junit.Test;

import static com.yoti.domain.Coordinates.fromXY;

public class GsonJSONSerializerTest {
    private  GsonJSONSerializer JSON = new GsonJSONSerializer();

    @Test
    public void knowsHowToSerializeCoordinates() {
        Assert.assertEquals("[1,2]", JSON.toJson(fromXY(1, 2)));
    }
    @Test
    public void knowsHowToDeserializeCoordinates() {
        Assert.assertEquals(fromXY(3, 4), JSON.fromJson("[3,4]", Coordinates.class));
    }

}
