# Urdu Font Comparator 

<br>

## Description

This project is about an Android application which allows users to explore various Urdu Fonts, but more importantly see and appreciate why Nasta'liq script should be a natural choice for Urdu content on mobile devices. 

## Motivation

<img src="https://i.imgur.com/LlKatq4.jpg" width="600" height="300" align="middle" alt="Source: Dawn Images">

Credits: [Dawn](http://herald.dawn.com/news/1153737/the-case-for-urdu-as-pakistans-official-language)

<br>

I wanted to play with Urdu fonts for a while but was still not getting a starting point or a solid motivation to do it. This idea originated when I read the news about a new and much improved font, [Meher Nasta'liq](http://csalt.itu.edu.pk/urdufont/) launched by [Center for Speech and Language Technologies (CSaLT)](http://csalt.itu.edu.pk/) lab at [International Technical University (ITU)](http://itu.edu.pk/), Lahore (Pakistan). The website claims it to be a step towards advanced Urdu Typography and a font which serves the need of modern mobile applications. Even Dawn covered a [story](https://www.dawn.com/news/1313737) about it being a Calligraphy-based Urdu font for the Internet. It was enough to catch my attention and I wanted to try it out and see how it is different from other fonts, especially Jameel Noori Nastaleeq Font, which is considered to be the best so far. Then once I started thinking about it, I realized that there is no app or resource where I can see how Urdu text looks like rendered in different fonts (there are a lot actually!).

As mentioned, I started this project to address a problem I was facing but in a broader picture, I am quite hopeful that it will encourage developers to create apps with Urdu content. 

## Background

Long story short, it can assist you in making a better decision on choosing the right Urdu font for your Android app or mobile app in general. The font is always a key element of the UI which impacts user's first impression of the app. It is believed that choice and attractiveness of the font makes the application visually appealing. The beauty, aesthetics and size of a font are one of the key factors. The choice of font matters a lot because it makes the text more natural to read and fairly easy to understand.

<img src="https://i.imgur.com/9nw5ttS.png" width="500" height="200" align="middle">

Credits: [Rekhta](https://rekhta.org/couplets/saliiqe-se-havaaon-men-jo-khushbuu-ghol-sakte-hain-unknown-couplets?lang=ur)

<br><br>

Urdu is my mother tongue, lingua franca as well as the national language of Pakistan and widely spoken in 6 countries by [60+ million people](http://mentalfloss.com/article/64594/proportional-map-worlds-largest-languages) but still Urdu as a language has not been successfully integrated with what we call "Digital Age". A significant number of Urdu speakers aren't technologically active, especially those residing in India and Pakistan due to poor economic conditions of the demographic. Among those active, majority of whom are Millennials don't write Urdu at all and are comfortable with using Roman Urdu as a medium of communication. 

I, myself, started using Urdu on Whatsapp and Messenger roughly 2 years ago after installing [SwiftKey Keyboard](https://play.google.com/store/apps/details?id=com.touchtype.swiftkey&hl=en) on my personal phone. It took like 3-4 months of regular usage before I became comfortable and developed fluent writing speed. Family and Friends poked fun and found it odd but I continued using it. Many of them later joined me after my constant encouragement. My International friends find Urdu script  quite fascinating and it's a shame that unlike every other countries, we don't write in our native language in daily lives. Many of us only realize it once we start living outside Pakistan.

> I am always sorry when any language is lost, because languages are the pedigrees of nations. ~ Samuel Johnson

Coming back to the topic, I feel like there are several problems which exist today. First, we only have handful of websites (e.g [BBC-Urdu](http://www.bbc.co.uk/urdu/)) entirely in Urdu and mobile apps are almost non-existent. Most of them tend to publish images instead of text which is highly non-productive. So there is an impression that market doesn't exist and users won't value it. Second, there's only a small chunk of websites (e.g [Jang News](https://jang.com.pk/)) which publishes content in Nasta'liq which is it's default writing style. Third, Google Translator performs extremely poor with English to Urdu translation, reducing reach. Last but not the least, there is a critical need to encourage our friends, family and especially millennials to start using Urdu as a communication medium and take pride in it.  


<img src="https://i.imgur.com/UpAfjQO.jpg" width="600" height="300" align="middle" alt="Source: Dawn Images">

Credits: [Dawn](http://herald.dawn.com/news/1153737/the-case-for-urdu-as-pakistans-official-language)

<br>

### Naskh vs Nasta'liq

By and large, Arabic today is written in Naskh and subsequently the Urdu text you see on Web is also predominantly in Naskh script. Urdu is not a child of Arabic but somehow Naskh was accepted as de facto style for Urdu on Internet. Urdu is typically written in a Nasta'liq style i.e. the connected letters in a word tend to follow a sloping baseline. The difference between Nasta'liq and Naskh is much more than just a different style and Nasta'liq is deeply connected to feelings of origin and pride of Urdu speakers (at least for those who care). Nasta'liq, based on a century-old calligraphic tradition, is considered one of the most beautiful scripts on the planet and also known as “the bride of calligraphy”. 

I would ignore geo-political affiliation for now but I can confidently say that the past two or three generations of Urdu readers and writers grew up associating Nasta'liq and Urdu almost exclusively with each other. Nasta'liq was the default script when reading Urdu books in schools. Therefore, our generation is so used to reading Urdu in Nasta'liq that we can't even read properly when written in Naskh, but due to the limited use of Nastal'iq, we are forced to read Urdu in Naskh on digital devices. I believe limited use of proper font for Urdu language has over the years led many people to lose interest in reading Urdu on Internet. 

[Ali Eteraz](https://twitter.com/eteraz) has written an awesome article on this topic titled, [The Death of the Urdu Script
](https://medium.com/@eteraz/the-death-of-the-urdu-script-9ce935435d90). Here is an example of Nasta'liq compared with the more common Naskh script.

![Naskh vs Nata'liq](https://cdn-images-1.medium.com/max/1600/0*9Cxd8PX0UX1WiU8b.jpeg)


The complexity of Nasta'liq makes it one of the world's most challenging writing styles. It should be immediately obvious why Naskh is easier to implement than Nasta'liq. Ali says “With its straightness and angularity, Naskh is simply easier to code, because unlike Nasta'liq, it doesn’t move vertically and doesn’t have dots adhering to a strict pattern.”
The difficulties in implementing Nasta'liq mean that most software companies have simply opted to implement Naskh in hopes that Urdu speakers will switch to using Naskh. This has not worked the way everyone might have anticipated. Apparently Naskh is so abhorrent to Urdu speakers that they prefer to switch to using the Roman script instead of Naskh on the Web and for texting and this is where things go wrong.

Nasta'liq occupies a special place in Urdu, and decades of attempts from press typesetting to moder-day Web have tried to squeeze Urdu into Naskh, but not with the same success as they have had with Farsi. Urdu people have managed to have a way to print Nastaleeq using technology bridges - discarding character typesetting for stencil-based presses, and in the modern day, using images instead of text. That hurts search, SEO and other text-oriented pursuits. While reading Urdu in Nasta'liq script, we don't actually read the letters, we are reading beautiful groups of letters that allow us to read very fast and recognizing the combination of characters (glyph in OTF). It is similar to the recognition of a character in Chinese that expresses a thought and not a letter. When those well-recognized Nasta'liq combinations do not appear to the reader, he/she is disoriented and does not like the printing of it.

#### This is how both scripts look like on a mobile screen

Nasta'liq on [Daily Pakistan](http://dailypakistan.com.pk/latest) website <br><br>
<img src="https://i.imgur.com/ned1c7q.png" width="200" height="400" align="center">

Naskh on [BBC Urdu](http://www.bbc.com/urdu/world) website <br><br>
<img src="https://i.imgur.com/lFUGYu0.png" width="200" height="400" align="center">

<br>

#### A brief overview of both scripts:

#####  Naskh 
Naskh though cursive is linear in nature. Each letter joins to the next moving from right to left. The glyphs change shape according to the place they occupy within the word. Strictly defined by norms laid down by tradition, the visual beauty of Naskh lies in the seamless manner in which each letter joins to the next letter to create a text which is as attractive as a visual drawing.

#####  Nasta'liq 
Nastaa'liq is one of the most complex scripts to design. Nasta'liq is written on a slanting baseline where the longer a word is, the taller the word becomes. Unlike Naskh which is linear in nature, Nastaa'liq moves both from right to left and vertically from top to bottom with the “nukte” or dots conforming to a strict placement order. Nasta'liq demands strict conformity with a specific grammar which has been honed and fine-tuned over the centuries. 

![Nasta'liq script representation](https://i.imgur.com/Ds8m2xD.png)

It's complexity also makes it one of the most difficult scripts to render using a computer font. Its right-to-left direction, vertical nature, and context-specific shaping provide a challenge to any font rendering engine and make it much more difficult to render than the flat (Naskh) Arabic script that it is based on. As a result, font developers have long struggled to produce a font with the correct shaping but at the same time avoid overlapping of dots and diacritics.


## Status of Urdu on Digital Devices

### Nasta'liq support

Nasta'liq became part of Windows 8 with introduction of [Urdu Typesetting](http://archives.miloush.net/michkap/archive/2011/11/16/10237715.html), a new member of the Arabic script font family. It was supposedly first widely available Unicode font to support Nasta'liq. Thanks to [Michael S. Kaplan and his team](http://archives.miloush.net/michkap/archive/2013/10/09/10455209.html) for this. 

Android started offering Urdu from [Marshmallow (Oct, 2015)](https://www.android.com/intl/en_in/versions/marshmallow-6-0/features/#marshmallow-features-1-body) and to my knowledge (at the moment of writing this) it still doesn't support Nasta'liq natively for Urdu language. 

[Mudassir Azeemi](https://twitter.com/maxeemi) was quite excited to inform us that Apple finally included Nasta'liq support in [iOS 11](https://www.apple.com/ios/ios-11-preview/) beta. He shared this news via a [facebook post](https://www.facebook.com/photo.php?fbid=10155735481406686&set=a.10150233210736686.314863.516911685&type=3&permPage=1) and we all hope to see the update reaching release.

### Urdu apps on Google Play

I started a research to find out Android apps which fulfill following criteria:

* Entire content is in Urdu
* Customized for [RTL](https://developer.android.com/about/versions/jelly-bean.html#42-native-rtl) (Right-to-left) layout mirroring
* Using a Nasta'liq font

It's an ongoing study and I will keep updating this section in future. Honestly, there aren't many apps which fulfill these criteria and that's exactly why it's important to highlight and encourage other apps creators. 

> Note: Right now, my app is also not configured for RTL layout mirroring in it's true sense and not all content is in Urdu but I will surely try to address both of these topics as an improvement in next update.

#### Hall of Fame

One app which I would like to mention is **[Pencil News](http://www.pencil.pk/home)**. It 
allows users to read news on an Urdu-based interface in authentic “Nasta'liq Urdu” font.

Pencil app fulfills the criteria mentioned above and have done a pretty good job with their app. It was also covered by [TechJuice](https://www.techjuice.pk/pencil-news-is-a-cool-urdu-news-application/) and you can also download it from [here](https://play.google.com/store/apps/details?id=com.pencil.news.android).

I am attaching some of the screenshots to give an idea how it should be done. 


<img src="https://i.imgur.com/oHPwhsh.png" width="200" height="400" align="center"> 
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<img src="https://i.imgur.com/WEpKcY9.png" width="200" height="400" align="center">


<br> <br>

## How can I contribute?

Nothing special, just fork and create a PR with your contributions. Later, I plan to define a certain criteria for those who want to include a new font in the app. 

## Where can I get more help, if I need it?

You can contact me (obviously!) at wahib.tech@gmail.com or tweet me at @wahibhaq. I will also try to get in contact with the R&D team behind [Center for Speech and Language Technologies (CSaLT)](http://csalt.itu.edu.pk/), [ITU](http://itu.edu.pk/). They can be of great help to us. 
