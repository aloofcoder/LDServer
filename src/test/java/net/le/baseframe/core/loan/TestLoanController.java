package net.le.baseframe.core.loan;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class TestLoanController {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testAddLoan() throws Exception {
        JSONObject json = new JSONObject();
        json.put("userId", "4");
        json.put("loanMoney", 100000);
        json.put("loanInterest", 40000);
        json.put("loanPeriods", 10);
        json.put("loanInterestPeriod", 0);
        json.put("loanType", 0);
        json.put("loanPledge", "车贷");
        json.put("loanStatus", 0);
        json.put("createUser", "韩乐");
        json.put("editUser", "韩乐");
        mockMvc.perform(MockMvcRequestBuilders.post("/loans","json").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).content(json.toString().getBytes()))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void getLoanByUserId () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loans/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
