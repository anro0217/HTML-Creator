# HTML-Creator
HTML képnézegető
----------------
Képeket tartalmazó mappákat és azok almappáit járja be, felkutatva az összes képet.
Mindegyikhez létrehoz egy html file-t, így a böngészőből megnyithatóvá válik egy egyszerű képnézegető.

Használata:

A Main.java fordítása után, futtatáskor parancssori argumentumként átadjuk a képek gyökérmappájának elérési útját.
A clearFolder.java file alján ugyan ez az elérési út megadása után, futtatásakor törölhető az összes korábban létrehozott html file a képek közül.

A Pictures.rar tartalmaz egy példaként létrehozott mappaszerkezetet.

Végezetül pedig megtalálható egy rename.py nevű file is, ebben ismételten átírva a saját elérési útra és megadva egy nevet a képeknek, futtatáskor az összes képet átnevez "megadott név" + 'futóindex' névre.
