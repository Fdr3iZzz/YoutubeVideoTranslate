from yt_dlp import YoutubeDL
import sys
import json

URLS = sys.argv[1]
print('URL: ' + URLS)

options = {'format': 'bestvideo+bestaudio/best',
           'outtmpl': {'default': 'C:/Users/Franz3/YoutubeVideoTranslator/%(title)s.%(ext)s'},
           'postprocessors': [{'api': 'https://sponsor.ajay.app',
                               'categories': {'filler',
                                              'interaction',
                                              'intro',
                                              'music_offtopic',
                                              'outro',
                                              'preview',
                                              'selfpromo',
                                              'sponsor'},
                               'key': 'SponsorBlock',
                               'when': 'after_filter'},
                              {'force_keyframes': False,
                               'key': 'ModifyChapters',
                               'remove_chapters_patterns': [],
                               'remove_ranges': [],
                               'remove_sponsor_segments': {'filler',
                                                           'interaction',
                                                           'intro',
                                                           'music_offtopic',
                                                           'outro',
                                                           'preview',
                                                           'selfpromo',
                                                           'sponsor'},
                               'sponsorblock_chapter_title': '[SponsorBlock]: '
                                                             '%(category_names)l'},
                              {'add_chapters': True,
                               'add_infojson': None,
                               'add_metadata': False,
                               'key': 'FFmpegMetadata'}],
           'subtitleslangs': ['en'],
           'verbose': True,
           'writeautomaticsub': True,
           'writesubtitles': True}
with YoutubeDL(options) as ydl:
    info = ydl.extract_info(URLS)
    with open('C:/Users/Franz3/YoutubeVideoTranslator/sponsorblockChapters.json', 'w') as outfile:
        outfile.write(json.dumps(info['sponsorblock_chapters']))
