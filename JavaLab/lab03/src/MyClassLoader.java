import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pas114 on 2016-10-20.
 */
public class MyClassLoader extends ClassLoader {

    /**
     * The HashMap where the classes will be cached
     */
    private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();


    @Override
    public String toString() {
        return MyClassLoader.class.getName();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if (classes.containsKey(name)) {
            return classes.get(name);
        }

        byte[] classData;

        try {
            classData = loadClassData(name);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class [" + name
                    + "] could not be found", e);
        }

        Class<?> c = defineClass(name, classData, 0, classData.length);
        resolveClass(c);
        classes.put(name, c);

        return c;
    }

    protected Class<?> findClassFromFile(String name,URL url) throws ClassNotFoundException {

        if (classes.containsKey(name)) {
            return classes.get(name);
        }

        try {
            URL[] urls = new URL[]{url};
            ClassLoader cl = new URLClassLoader(urls);
            System.out.println("URL : "+url.toString()+" Name : "+name);
            Class cls = cl.loadClass(name);

        return cls;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException("Class [" + name
                    + "] could not be found", e);

        }
    }

    /**
     * Load the class file into byte array
     *
     * @param name
     *            The name of the class e.g. com.codeslices.test.TestClass}
     * @return The class file as byte array
     * @throws IOException
     */
    private byte[] loadClassData(String name) throws IOException {
        BufferedInputStream in = new BufferedInputStream(
                ClassLoader.getSystemResourceAsStream(name.replace(".", "/")
                        + ".class"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        return getBytes(in, out);
    }
    private byte[] loadClassDataFromFile(File file) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        return getBytes(new FileInputStream(file), out);
    }


    private byte[] getBytes(InputStream in, ByteArrayOutputStream out) throws IOException {
        int i;

        while ((i = in.read()) != -1) {
            out.write(i);
        }

        in.close();
        byte[] classData = out.toByteArray();
        out.close();

        return classData;
    }

    public void unload(Class classToUload) {
        classes.remove(classToUload.getName());
    }
}

