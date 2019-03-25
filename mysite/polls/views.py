from django.shortcuts import render
from django.http import HttpResponse
import datetime
#29 March 2019 at 11 pm UK time
def index(request):
    now = datetime.datetime.now()
    brexit = datetime.datetime(2019, 3, 29, 11)
    until = brexit - now
    return render(request, 'brexit/time.html', {'tijd': until})
