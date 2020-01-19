IJ IDEA Window中文乱码
  1. settings中的文件编码
  2. 安装目录
     文件idea64.exe.vmoptions末尾添加-Dfile.encoding=UTF-8
  3. 配置目录(缓存目录) C:\Users\xj_tj\.IntelliJIdea2019.3\config
     文件idea.exe.vmoptions和idea64.exe.vmoptions末尾添加-Dfile.encoding=UTF-8
  4. IntelliJ IDEA>File>Setting>Editor>File Encodings
     将Global Encoding、Project Encoding、Default encodeing for properties files这三项都设置成UTF-8
  5. 重启

中国科技资源共享网对接测试
从多个第三方认证中心登录的问题
1. 用户ID唯一，两个认证中心，用户ID相同(邮件地址)，视为同一用户
2. 通过state判断来自哪个认证中心
3. 或者用session记录认证方式
