from django.urls import path
from . import views

app_name = 'polls'

urlpatterns = [
    path('time/', views.index, name='index')
]
