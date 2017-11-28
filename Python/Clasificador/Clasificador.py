from monkeylearn import MonkeyLearn
 
mlTweet = MonkeyLearn('693efd3bdee8bc21dc42a02fa9cb291e86073a0b')
mlNoticias = MonkeyLearn('bfec1d49b85f0fd0849f8244f4dd599c0080aae8')
mlZona = MonkeyLearn('aaa6317e0336ea1698db5d01c2aed231abd4c1a1')

class ClasificadorClass:
    def clasificarTweets(self, tweet):
        module_id = 'cl_fEWNwQYz'
        res = mlTweet.classifiers.classify(module_id, tweet, sandbox=True)
        resultado = res.result
        categoria = resultado[0][0]["label"]
        print(categoria)
        return categoria

    def clasificadorNoticias(self, noticia):
        module_id = 'cl_Bpu2WWBD'
        res = mlNoticias.classifiers.classify(module_id, noticia, sandbox=True)
        resultado = res.result
        categoria = resultado[0][0]["label"]
        print(categoria)
        return categoria
    def clasificadorZona(self, tweet):
             module_id = 'cl_iYYd3Hj2'
             res = mlZona.classifiers.classify(module_id, tweet, sandbox=True)
             resultado = res.result
             zona = resultado[0][0]["label"]
             print(zona)
             return zona
    



        
