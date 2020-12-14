package com.after.aliBaBa;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageResponse;
import com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest;
import com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoResponse;
import com.aliyuncs.imagerecog.model.v20190930.TaggingImageRequest;
import com.aliyuncs.imagerecog.model.v20190930.TaggingImageResponse;
import com.aliyuncs.ocr.model.v20191230.RecognizeCharacterRequest;
import com.aliyuncs.ocr.model.v20191230.RecognizeCharacterResponse;
import com.aliyuncs.profile.DefaultProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 文字识别
 */
public class test2 {

    static IAcsClient client = null;

    public static void main(String[] args) throws Exception {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI4GKdsdsfsdewsSEFE8n5Cy", "SWbwTnFDIkfDKFOLjdaxlTL");
        client = new DefaultAcsClient(profile);

        //场景识别
//        testRecognizeScene();

        //logo 识别
//        logo();
//       鉴黄
//        identifyPornRequest();

        //通用图像打标
        testTaggingImage();
    }

    //通用图像打标
    public static void testTaggingImage() throws Exception {
        System.out.println("--------  通用图像打标 --------------");
        TaggingImageRequest req = new TaggingImageRequest();
    // 注意：下面的链接换成自有的oss链接
    req.setImageURL(
        "http://viapi-test.oss-cn-shanghai.aliyuncs.com/sanjiye-meizi/%E9%80%9A%E7%94%A8%E6%89%93%E6%A0%87.jpg");
        TaggingImageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
    }
    //图片涉政暴恐识别、图片鉴黄
    private static void identifyPornRequest() throws Exception {

        ScanImageRequest req = new ScanImageRequest();
        List<String> scenes = new ArrayList<String>();
//        scenes.add("porn");
//        scenes.add("terrorism");
//        scenes.add("live");
        scenes.add("terrorism");


        req.setScenes(scenes);
        List<ScanImageRequest.Task> tasks = new ArrayList<ScanImageRequest.Task>();
        com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest.Task task = new ScanImageRequest.Task();
        task.setDataId(UUID.randomUUID().toString());
    task.setImageURL(
            "http://viapi-test.oss-cn-shanghai.aliyuncs.com/sanjiye-meizi/%E9%A3%8E%E9%99%A9%E4%BA%BA%E7%89%A9.jpg");
//            "http://explorer-image.oss-cn-shanghai.aliyuncs.com/6MgqV40lZ2hrJGiVeBoz89fa/timg.jpeg?OSSAccessKeyId=LTAI4Fk9FstqSEYnqKJ5Dpeo&Expires=1607674828&Signature=cxaIhQsjHVyWRh1yB1NroEWNGYw%3D");
//            "http://viapi-test.oss-cn-shanghai.aliyuncs.com/sanjiye-meizi/%E5%9B%BE%E7%89%87%E6%99%BA%E8%83%BD%E9%89%B4%E9%BB%84.jpg");
//        "http://explorer-image.oss-cn-shanghai.aliyuncs.com/6MgqV40lZ2hrJGiVeBoz89fa/WechatIMG24.jpeg?OSSAccessKeyId=LTAI4Fk9FstqSEYnqKJ5Dpeo&Expires=1607674113&Signature=vkKCFkci%2BAJVriM7fNGWYweTCB8%3D");
        tasks.add(task);
        req.setTasks(tasks);

        ScanImageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
    }

    // logo识别
    public static void logo(){
        System.out.println("--------  logo识别 --------------");
        com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest request = new com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest();
        request.setRegionId("cn-shanghai");
        List<com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest.Tasks> tasksList = new ArrayList<com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest.Tasks>();
        com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest.Tasks tasks1 = new RecognizeLogoRequest.Tasks();
    tasks1.setImageURL(
        "https://after90.oss-cn-shanghai.aliyuncs.com/u%3D3435029495%2C1941229217%26fm%3D26%26gp%3D0.jpg");
        tasksList.add(tasks1);
//        com.aliyuncs.imagerecog.model.v20190930.RecognizeLogoRequest.Tasks tasks2 = new RecognizeLogoRequest.Tasks();
//    tasks2.setImageURL(
//        "https://after90.oss-cn-shanghai.aliyuncs.com/u%3D352054485%2C1006342271%26fm%3D26%26gp%3D0.jpg");
//        tasksList.add(tasks2);
        request.setTaskss(tasksList);

        try {
            RecognizeLogoResponse response = client.getAcsResponse(request);
            printResponse(request.getSysActionName(), response.getRequestId(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //场景识别
    public static void testRecognizeScene() throws Exception {
        System.out.println("--------  场景识别 --------------");

        RecognizeCharacterRequest request = new RecognizeCharacterRequest();
//        request.setRegionId("cn-shanghai");
        request.setMinHeight(10);
        request.setOutputProbability(true);
        request.setImageURL("https://after90.oss-cn-shanghai.aliyuncs.com/timg%20%281%29.jpeg");

        try {
            RecognizeCharacterResponse resp = getAcsResponse(request);

//            RecognizeCharacterResponse resp = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(resp));
            printResponse(request.getSysActionName(), resp.getRequestId(), resp);

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printResponse(String actionName, String requestId, AcsResponse data) {
        System.out.println(String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId,
                JSON.toJSONString(data) ));
    }

    private static <R extends RpcAcsRequest<T>, T extends AcsResponse> T getAcsResponse(R req) throws Exception {
        try {
            return client.getAcsResponse(req);
        } catch (ServerException e) {
            // 服务端异常
            System.out.println(String.format("ServerException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (ClientException e) {
            // 客户端错误
            System.out.println(String.format("ClientException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            throw e;
        }
    }


}
