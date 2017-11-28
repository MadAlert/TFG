import tweepy
import io
import json
import var
import time

from tweepy import Stream
from tweepy.streaming import StreamListener

fichero = "tweets"
usuarios = var.var()

class listener(StreamListener):
    def on_data(self, data):
        try:
            fechaActual = time.strftime("%d%m%y")
            file = fichero+fechaActual+".json"
            with io.open(file, 'a', encoding='utf-8', buffering=1) as f:
                data.rstrip('\n')
                carga = json.loads(data)
                mencionVacia = False
                mencionInteresa = False
                #existe mencion
                if "entities" in carga:
                    #Obtenemos la mencion
                    mencion =carga["entities"]["user_mentions"]
                    #La lista de menciones es vacia, es porque no es una mencion
                    if (not mencion):
                        mencionVacia= True
                    #Es una mencion de un usuario de la lista a otro
                    elif ((mencion[0]["id_str"]in usuarios) and (carga["user"]["id_str"] in usuarios)):
                            mencionInteresa= True
                if "created_at" in carga and not("RT" in carga["text"]) and (mencionVacia or mencionInteresa):
                    f.write(u'{0}\n'.format(json.dumps(carga, ensure_ascii=False)))
                    print("Me sirve")
                else:
                    print("Este tweet no me importa")
            return True
        except BaseException as e:
            print ("Error" + e)
        return True

    def on_error(self, status):
        print(status)
        return True
