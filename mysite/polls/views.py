from django.shortcuts import render
from django.http import HttpResponse
import datetime
#29 March 2019 at 11 pm UK time
def index(request):
    ukhour = 11
    hour = ukhour + 1
    now = datetime.datetime.now()
    brexit = datetime.datetime(2019, 3, 29, hour)
    until = brexit - now
    return render(request, 'brexit/time.html', {'tijd': until})

