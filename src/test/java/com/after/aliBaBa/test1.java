package com.after.aliBaBa;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class test1 {

        public static void main(String[] args) {
    // 创建DefaultAcsClient实例并初始化
    DefaultProfile profile =
        DefaultProfile.getProfile(
            "cn-hangzhou", // 地域ID
            "LTAI4FzdsdsPUSQKZuy", // RAM账号的AccessKey ID
            "rMLJUKEdsfsdfrVQmUtRD"); // RAM账号AccessKey Secret
            IAcsClient client = new DefaultAcsClient(profile);
            // 创建API请求并设置参数
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            request.setPageSize(10);
            // 发起请求并处理应答或异常
            DescribeInstancesResponse response;
            try {
                response = client.getAcsResponse(request);
                for (DescribeInstancesResponse.Instance instance:response.getInstances()) {
                    System.out.println(instance.getPublicIpAddress());
                }
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }

}
