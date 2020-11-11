package com.jc.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/26 20:53
 */
public class TestVod {
    public static void main(String[] args) {
        String accessKeyId = "LTAI4G7QSQjyJETHLUnCh8pH";
        String accessKeySecret = "D5m2Hq56n7vqUhdehADIyBASUwYi0N";

        String title = "6 - What If I Want to Move Faster - upload by sdk";   //上传之后文件名称
        String fileName = "/Users/jiangcheng/Downloads/在线教育--谷粒学院/项目资料/1-阿里云上传测试视频/6 - What If I Want to Move Faster.mp4";  //本地文件路径和名称
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /*获取播放凭证函数*/
    public static void getVideoPlayAuth() throws Exception {
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G7QSQjyJETHLUnCh8pH", "D5m2Hq56n7vqUhdehADIyBASUwYi0N");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("be908998aec543daaa811d9179bc8cea");
        response = client.getAcsResponse(request);
        System.out.println("playAuth:" + response.getPlayAuth());
    }

    /*获取播放地址函数*/
    public static void getPlayInfo() throws Exception {
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G7QSQjyJETHLUnCh8pH", "D5m2Hq56n7vqUhdehADIyBASUwYi0N");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象里面设置视频id
        request.setVideoId("be908998aec543daaa811d9179bc8cea");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    @Test
    public void test() throws Exception {
        getVideoPlayAuth();
        getPlayInfo();
    }


}
