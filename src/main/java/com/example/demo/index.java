@Controller
public class WellComm {

    @RequestMapping("index")
    public String index(){
        return "indes";
    }

    @RequestMapping("aaa")
    public void vodeo(HttpServletResponse response){
        try {
            OutputStream output = response.getOutputStream();
            FileInputStream input = new FileInputStream("D:\\Youxun\\patcher\\skin\\jzmb111.mp4");
            /*byte[] bts = new byte[819200];
            int len = -1;
            while((len=input.read(bts))!=-1){
                output.write(bts,0,len);
            }*/

            //获取输入输出通道
            FileChannel fcin = input.getChannel();
            //创建1024字节的缓存区
            ByteBuffer buffer =  ByteBuffer.allocate(1024000);
            long start = System.currentTimeMillis();
            while(true){
                //重设此缓冲区，使它可以接受读入的数据。Buffer对象中的limit=capacity;
                buffer.clear();
                //从输入通道中将数据读入到缓冲区
                int temp = fcin.read(buffer);
                if(temp == -1){
                    break;
                }
                //让缓冲区将新入读的输入写入另外一个通道.Buffer对象中的limit=position
                buffer.flip();
                //从输出通道中将数据写入缓冲区
                //fcout.write(buffer);
                output.write(buffer.array(),0,-1);
            }
            fcin.close();
            System.out.println("总共耗时:"+(System.currentTimeMillis()-start)+"ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
