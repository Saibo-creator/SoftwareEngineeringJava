public class ProxyFile implements File{
    private File realFile;
    public ProxyFile() {
        realFile=new RealFile();
    }

    @Override
    public String read(String fileName) {
        if (fileName.contains("sensitive")){
            throw new RuntimeException();
        }
        return realFile.read(fileName);
    }

    @Override
    public void write(String fileName, String data) {
        if (fileName.contains("sensitive")){
            throw new RuntimeException();
        }
        realFile.write(fileName,data);

    }
}
