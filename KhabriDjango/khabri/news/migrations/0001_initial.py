# Generated by Django 5.0.6 on 2024-08-25 11:15

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='News',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=255)),
                ('description', models.TextField()),
                ('content', models.TextField()),
                ('url', models.URLField(max_length=500)),
                ('image', models.URLField(blank=True, max_length=500, null=True)),
                ('published_at', models.DateTimeField()),
                ('source_name', models.CharField(max_length=255)),
                ('source_url', models.URLField(max_length=500)),
            ],
        ),
    ]
