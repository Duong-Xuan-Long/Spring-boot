```bash
Thêm file system.properties vào src :viết là
java.runtime.version=17
Bước 1 tạo project 
$ heroku create [project_name]
Bước 2:Login to Heroku 
$ heroku login
Bước 3:khởi tạo git
$ git init
Bước 4: Add code
git add .
Bước 5 :Commit code
$ git commit -m"update"
Bước 6:Liên kết local repo với heroku git repo
$ heroku git:remote -a mid-term-22
Bước 7:Đẩy code lên heroku git repo
$ git push heroku master
```midterm-test-ok