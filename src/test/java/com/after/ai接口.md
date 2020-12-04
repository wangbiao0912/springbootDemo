## 1.图片文字识别
* 接口名称  
```
    POST    http://182.43.224.154:8093/predict/chinese_ocr_db_crnn_mobile
```

* 请求参数
```
    {"images": ["base64编码"]}
```

* 请求结果
```
res (list[dict]): 识别结果的列表，列表中每一个元素为 dict，各字段为：
data (list[dict]): 识别文本结果，列表中每一个元素为 dict，各字段为：
text(str): 识别得到的文本
confidence(float): 识别文本结果置信度
text_box_position(list): 文本框在原图中的像素坐标，4*2的矩阵，依次表示文本框左下、右下、右上、左上顶点的坐标 如果无识别结果则data为[]
save_path (str, optional): 识别结果的保存路径，如不保存图片则save_path为''

  {
      "msg": "",
      "results": [
          {
              "data": [
                  {
                      "confidence": 0.8737552762031555,
                      "text": "XX省XX监狱",
                      "text_box_position": [
                          [
                              136,
                              43
                          ],
                          [
                              354,
                              43
                          ],
                          [
                              354,
                              75
                          ],
                          [
                              136,
                              75
                          ]
                      ]
                  },
                          {
                                       "confidence": 0.9882618188858032,
                                       "text": "最新动态",
                                       "text_box_position": [
                                           [
                                               834,
                                               130
                                           ],
                                           [
                                               953,
                                               130
                                           ],
                                           [
                                               953,
                                               163
                                           ],
                                           [
                                               834,
                                               163
                                           ]
                                       ]
                                   }
              ],
              "save_path": ""
          }
      ],
      "status": "0"
  }

```

## 2.文字鉴黄
* 接口名称
```
    post    http://182.43.224.154:8094/predict/porn_detection_lstm
```

* 请求参数
```
{"texts": ["黄片下载", "需要鉴黄的内容"], "batch_size": 10, "use_gpu":false}
```

* 请求结果
```
   {
       "msg": "",
       "results": [
           {
               "not_porn_probs": 0.0121,
               "porn_detection_key": "porn",
               "porn_detection_label": 1,
               "porn_probs": 0.9879,
               "text": "黄片下载"
           },
           {
               "not_porn_probs": 0.9999,
               "porn_detection_key": "not_porn",
               "porn_detection_label": 0,
               "porn_probs": 0.0001,
               "text": "需要鉴黄的内容"
           }
       ],
       "status": "0"
   }

```
