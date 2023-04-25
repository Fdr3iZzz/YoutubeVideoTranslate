from yt_dlp import YoutubeDL
import sys

URLS = sys.argv[1]
print('URL: ' + URLS)
options = {
    'format': 'bestvideo+bestaudio/best',
    'sponskrub_remove': True,
    'sponskrub_mark': True,
    'embed_subs': True,
    'add_metadata': True,
    'outtmpl': 'C:/Users/Franz3/YoutubeVideoTranslator/%(title)s.%(ext)s',
    'verbose': True
}
download_finished = False
def my_hook(d):
    global download_finished
    if d['status'] == 'finished':
        download_finished = True

with YoutubeDL(options) as ydl:
    ydl.add_progress_hook(my_hook)
    ydl.download(URLS)

#print(download_finished)
#options = {
#    'format': 'bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best',
#    'merge_output_format': 'mp4',
#}