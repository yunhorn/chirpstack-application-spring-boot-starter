import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class StringToMapTest {

    @Test
    public void convert(){
        String str = "{\"error\":\"authentication failed: jwt parse error: token is expired by 48h24m0s\",\"code\":16,\"message\":\"authentication failed: jwt parse error: token is expired by 48h24m0s\",\"details\":[]}";
        Map<String, Object> stringObjectMap = JSONUtils.jsonToMap(str);
        Assertions.assertEquals(true,stringObjectMap.containsKey("error"));
        Assertions.assertEquals("authentication failed: jwt parse error: token is expired by 48h24m0s",stringObjectMap.get("error"));
        Assertions.assertEquals(true,stringObjectMap.get("error").toString().contains("authentication failed"));
    }

}
