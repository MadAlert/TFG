import json
import requests

# We define the variables need to call the API
api = 'http://api.meaningcloud.com/class-1.1'
keyNoticias = '05ed9a7c754aeee5d5f99470a756a5f8'
modelNews = 'news'

class ClasificadorClass:
    #def clasificarTweets(self, tweet):
       

    def clasificadorNoticias(self, noticia):
        parameters = {'key': keyNoticias, 'model': modelNews, 'txt': noticia}
        r = requests.request('POST', api, data=parameters)
        response = r.content
        response_json = json.loads(response.decode('utf-8'))
        cat = response_json['category_list'][0]['label']
        print(cat)
        print("\n")
        return cat

        
