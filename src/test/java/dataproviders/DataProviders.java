package dataproviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import utils.JSONUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProviders {

    @DataProvider(name="postData")
    public Object[][] getPostData(){
        return new Object[][]{
            {"Title 1","Body 1",1},
            {"Title 2","Body 2",2},
            {"Title 3","Body 3",3}
        };
    }
    @DataProvider(name="postJsonData")
    public Object[][] getJsonPostData() throws IOException {
        String jsonConent = JSONUtils.readJsonFile("postTestData.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> datalist= mapper.readValue(jsonConent,new TypeReference<List<Map<String,Object>>>(){});
        Object[][] data= new Object[datalist.size()][1];
        for(int i=0;i<data.length;i++){
            data[i][0]=datalist.get(i);

        }
        return data;
    }

    @DataProvider(name="excelPostData")
    public Object[][] getExcelPostData() throws IOException{
        return JSONUtils.readExcelData("testData.xlsx","PostData");
    }
}
