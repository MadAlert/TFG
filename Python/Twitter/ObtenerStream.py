#Obtener tweet en stream de un usuario x
import hidden
import MyListener
import tweepy
import json
import var
import time

from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
from MyListener import listener


#Funcion que llama al stream de los usuarios que deseemos
def streamUsuario(user):
    keys = hidden.oauth()
    auth = OAuthHandler(keys['consumer_key'], keys['consumer_secret'])
    auth.set_access_token(keys['access_token'], keys['access_secret'])

    listen = listener()
    twitter_stream = Stream(auth, listen)
    twitter_stream.filter(follow=user)

    
#Main
usuarios = var.var()
streamUsuario(usuarios)
