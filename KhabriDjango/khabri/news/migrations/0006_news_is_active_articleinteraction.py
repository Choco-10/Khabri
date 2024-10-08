# Generated by Django 5.0.6 on 2024-08-25 18:46

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('news', '0005_news_category_news_country'),
    ]

    operations = [
        migrations.AddField(
            model_name='news',
            name='is_active',
            field=models.IntegerField(default=1),
        ),
        migrations.CreateModel(
            name='ArticleInteraction',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user_id', models.CharField(max_length=255)),
                ('is_liked', models.BooleanField(default=False)),
                ('time_spent', models.FloatField(default=0)),
                ('is_reported', models.BooleanField(default=False)),
                ('no_of_views', models.PositiveIntegerField(default=0)),
                ('article', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='interactions', to='news.news')),
            ],
        ),
    ]
