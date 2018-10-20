package net.le.baseframe.core.manager;

import com.alibaba.fastjson.JSONObject;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestManagerController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void init () {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSearchManagers() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("pageNum", "1");
        map.add("pageSize", "10");
        mockMvc.perform(MockMvcRequestBuilders.get("/managers")
                .params(map)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddManager () throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("managerNumber", "18149197090");
        jsonObject.put("managerPwd", "123456");
        jsonObject.put("managerName", "韩乐");
        jsonObject.put("managerStatus", "0");
        jsonObject.put("createUser", "韩乐");
        jsonObject.put("editUser", "韩乐");
        mockMvc.perform(MockMvcRequestBuilders.post("/managers", "json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString().getBytes()))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
