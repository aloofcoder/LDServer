package net.le.baseframe.core.user;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试cotroller
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestUserController {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllUser () throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("pageNum", "1");
        params.add("pageSize", "10");
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .params(params)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser() throws Exception {
        JSONObject json = new JSONObject();
        json.put("userName", "韩乐");
        json.put("userGender", 0);
        json.put("userMobile", "15098102029");
        json.put("userAddress", "咸阳");
        json.put("userIdCard", "6104251890518194");
        json.put("userIdCardUrl", "d,/idcard");
        json.put("userImgUrl", "d,/imgurl");
        json.put("linkmanName1","韩乐1");
        json.put("linkmanMobile1", "15098102021");
        json.put("linkmanName2","韩乐2");
        json.put("linkmanMobile2", "15098102023");
        json.put("linkmanName3","韩乐3");
        json.put("linkmanMobile3", "15098102022");
        json.put("userStatus",0);
        json.put("createUser", "韩乐");
        json.put("editUser", "韩乐");
        System.out.println(json);
        mockMvc.perform(MockMvcRequestBuilders.post("/users","json")
                .characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON).content(json.toString().getBytes()))
        .andExpect(status().isCreated())
        .andDo(print());
    }



    @Test
    public void testGetUer () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/20181009233548157").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoveUser () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/20181009233548157").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
