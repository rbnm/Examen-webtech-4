from django.shortcuts import render
from django.http import HttpResponse


def index(request):
    
   text = """<h1>welcome to my app !</h1>"""
   return HttpResponse(text)

