import com.jack.Annotations.Component;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class annocationScanTest {
    private Map<String,Object> Objects = new HashMap();
    @Test
    public void ScanTest(){
        String scanPackagePath = "com.jack.beans";
        String scanPath = scanPackagePath.replaceAll("\\.", "/");
        URL systemResource = ClassLoader.getSystemResource(scanPath);
        String absolutePath = systemResource.getPath();
        File file = new File(absolutePath);
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f->{
            String FileName = f.getName().split("\\.")[0];
            try{
                Class<?> aClass = Class.forName(scanPackagePath+"."+FileName);
                if(aClass.isAnnotationPresent(Component.class)){
                    Component annotation = aClass.getAnnotation(Component.class);
                    String value = annotation.value();
                    Objects.put(value,aClass.newInstance());
                };
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        System.out.println(Objects);


    }
}
