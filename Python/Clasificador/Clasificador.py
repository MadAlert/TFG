import json
import requests

from monkeylearn import MonkeyLearn

# We define the variables need to call the API
api = 'http://api.meaningcloud.com/class-1.1'
keyNoticias = '05ed9a7c754aeee5d5f99470a756a5f8'
modelNews = 'news'
keyTwitter = 'cd92195fe6c9cc401ab69109099798aa'
modelTwitter = 'tweets'

mlZona = MonkeyLearn('aaa6317e0336ea1698db5d01c2aed231abd4c1a1')

class ClasificadorClass:
    #def clasificarTweets(self, tweet):
       

    def clasificadorNoticias(self, noticia):
        parameters = {'key': keyNoticias, 'model': modelNews, 'txt': noticia}
        r = requests.request('POST', api, data=parameters)
        response = r.content
        response_json = json.loads(response.decode('utf-8'))
        if(response_json['category_list']):
            cat = response_json['category_list'][0]['label']
        else:
            cat = "Nada"
        print(cat)    
        print("\n")
        return cat
    
    def clasificarTweets(self, tweet):
        parameters = {'key': keyTwitter, 'model': modelTwitter, 'txt': tweet}
        r = requests.request('POST', api, data=parameters)
        response = r.content
        response_json = json.loads(response.decode('utf-8'))
        if(response_json['category_list']):
            cat = response_json['category_list'][0]['label']
        else:
            cat = "Nada"
        print(cat)    
        print("\n")
        return cat
        

    def clasificadorZona(self, tweet):
        module_id = 'cl_iYYd3Hj2'
        res = mlZona.classifiers.classify(module_id, tweet, sandbox=True)
        resultado = res.result
        zona = resultado[0][0]["label"]
        print(zona)
        return zona

        
