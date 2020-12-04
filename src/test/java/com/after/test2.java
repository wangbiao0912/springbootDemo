//package com.after;
//
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
//import io.netty.handler.codec.EncoderException;
//import java.io.File;
//import lx.jave.Encoder;
//import lx.jave.MultimediaInfo;
//import lx.jave.VideoInfo;
//import lx.jave.VideoSize;
//import ws.schild.jave.InputFormatException;
//
//
//public class test2 {
//
//
////  public static void test(MultipartFile multipartFile) throws Exception {
////    Encoder encoder = new Encoder();
////    File file = new File(multipartFile.getOriginalFilename());
////    InputStream inputStream = multipartFile.getInputStream();
////    FileUtils.copyInputStreamToFile(inputStream, file);
////    MultimediaInfo encoderInfo = encoder.getInfo(file);
////    //视频播放时长
////    long duration = encoderInfo.getDuration();
////    logger.debug("视频播放时长:{}秒", duration / 1000);
////    //多媒体文件格式名称
////    String encoderInfoFormat = encoderInfo.getFormat();
////    logger.debug("多媒体文件格式名称:{}", encoderInfoFormat);
////    //音频 返回一组特定于音频的信息。如果为空，则多媒体文件中没有音频*流。
////    AudioInfo audio = encoderInfo.getAudio();
////    if (audio != null) {
////      //音频流解码器名称
////      String audioDecoder = audio.getDecoder();
////      logger.debug("音频流解码器名称:{}", audioDecoder);
////    }
////    //视频
////    VideoInfo videoInfo = encoderInfo.getVideo();
////    if (videoInfo == null) {
////      throw new RuntimeException("多媒体文件中没有视频流");
////    }
////    //视频流解码器名称
////    String videoInfoDecoder = videoInfo.getDecoder();
////    logger.debug("视频流解码器名称:{}", videoInfoDecoder);
////    //返回视频大小。如果为空，则此信息不可用
////    VideoSize videoInfoSize = videoInfo.getSize();
////    if (videoInfoSize == null) {
////      throw new RuntimeException("视频分辨率获取失败");
////    }
////    //视频高度
////    int height = videoInfoSize.getHeight();
////    //视频宽度
////    int width = videoInfoSize.getWidth();
////    logger.debug("视频高度:{},视频宽度:{}", height, width);
////  }
//
//    public static void main(String[] args) throws InputFormatException, EncoderException, Exception {
////    FFMPEGLocator ffmpegLocator=new FFMPEGLocator()
//    /**
//     * 获取本地多媒体文件信息
//     */
//    // 编码器
////        String path="'rtsp://172.23.35.208:554/live/ch12072117142442867381.mpg?userid=i520661736@itv&stbip=172.254.193.196&clienttype=1&mediaid=00000000040049640131&ifcharge=1&time=20201026112511+08&life=172800&usersessionid=1786763&vcdnid=vcdn001&boid=vcdn001&ContentID=00000000040049640131&srcboid=vcdn001&columnid=&backupagent=172.23.35.208:554&ctype=4&playtype=2&Drm=0&EpgId=null&programid=00000000040049640131&contname=&fathercont=ch12072117142442867381&programbegin=20201020013500+08&programend=20201020013700+08&utcprogrambegin=20201019173500+00&utcprogramend=20201019173700+00&bp=0&authid=0&tscnt=0&tstm=0&tsflow=0&ifpricereqsnd=1&stbid=031004990070321000007497816E3ACB&nodelevel=3&terminalflag=1&bitrate=0&AuthInfo=mVTtG5tnJ7hg275bO7kK8FEc6sUXyKp%2BGn6c%2Bq31dmeeOumhrbj3UfgmbWWMuHz2OYQS9BmDBnoTlX2MPrBFAA%3D%3D&tvodtype=2&usercharge=1973B0B7CD8476A57861FC89E592B9C1'";
//    String path="http://182.43.229.186:8085/o3074gpeo7k.p701.1.mp4";
//
////    if(path.indexOf("http") != -1) {
//////      path = source.getPath();
////      path = path.split(":")[0] + "://" + path.split(":")[1].substring(1);
////      path = path.replace("\\", "/");
////    }
//    Encoder encoder = new Encoder();
//    File file = new File(path);
//    // 多媒体信息
//    MultimediaInfo info = encoder.getInfo(file);
//    // 时长信息
//    long duration = info.getDuration();
//    System.out.println("视频时长为：" + duration / 1000 + "秒");
//    // 音频信息
////    AudioInfo audio = info.getAudio();
////    int bitRate = audio.getBitRate();  // 比特率
////    int channels = audio.getChannels();  // 声道
////    String decoder = audio.getDecoder();  // 解码器
////    int sRate = audio.getSamplingRate();  // 采样率
////    System.out.println("解码器：" + decoder + "，声道：" + channels + "，比特率：" + bitRate + "，采样率：" + sRate);
//    // 视频信息
//    VideoInfo video = info.getVideo();
//    int bitRate2 = video.getBitRate();
//    Float fRate = video.getFrameRate();  // 帧率
//    VideoSize videoSize = video.getSize();
//    int height = videoSize.getHeight();  // 视频高度
//    int width = videoSize.getWidth();  // 视频宽度
//    System.out.println("视频帧率：" + fRate + "，比特率：" + bitRate2 + "，视频高度：" + height + "，视频宽度：" + width);
//  }
//
//
//}
