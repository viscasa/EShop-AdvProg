
<div align="center">
    <h1>Aplikasi eShop Pemrograman Lanjut</h1>
</div>

<div align="center">
    <img src="src/main/resources/static/image/burhan.png" alt="burhan" width="200"/>
</div>

<div align="center">
    <h2>Alwie Attar Elfandra</h2>
    <h2>2306241726</h2>
</div>

<details>
<summary>Modul 1</summary>

## Reflection 1

Setelah mengerjakan ketiga fitur ini, saya telah menerapkan beberapa _coding standard_ seperti _clean code principles_ yaitu membuat kode yang mudah dibaca, membuat _method-method_ yang masing-masingnya hanya menangani satu tugas spesifik, dan penggunaan _method_ untuk menghindari penulisan kode yang sama berulang kali. Selain _clean code_ saya juga menggunakan git flow untuk pembuatan fitur-fitur saya. Masing-masing fitur yang saya buat memiliki _branch_-nya sendiri pada repositori saya. Terakhir saya juga mengimplementasikan _secure coding_, untuk secure coding sendiri belum banyak yang saya lakukan karena belum adanya user pada fitur-fitur ini. Hal yang sudah saya implementasikan antara lain memastikan input _quantity_ pada produk adalah sebuah angka dan memastikan tidak terjadinya error pada program.

Beberapa kesalahan yang terjadi/hal-hal yang belum saya lakukan ketika saya membuat fitur-fitur ini antara lain mengsanitasi input, pemberian batas pada input, kurangnya komentasi pada program, dan masih banyak lagi. Hal yang dapat saya perbaiki kedepannya adalah memastikan bahwa kode saya sudah memenuhi _coding standard_, memastikan input pada program sesuai dengan tujuannya, memperbaiki tampilan yang dihasilkan program, dan memastikan pengerjaan code sesuai dengan branchnya.

## Reflection 2

Setelah membuat unit test, banyak hal yang telah saya pelajari. Saya juga menemukan beberapa bug pada program saya sehingga saya bisa melakukan fix terhadap program saya. Menurut saya jumlah unit test tidak penting, tetapi apakah unit test sudah meng-_cover_ semua kemungkinan pada code kita. Selain itu, menurut saya walaupun saya sudah memiliki 100% code coverage bukan berarti code saya tidak ada bug/error. Code coverage dinilai dari unit test yang kita buat sementara belum tentu unit test yang kita buat sudah memastikan bahwa semua code kita berjalan lancar.

Untuk pembuatan unit test baru, saya baru menyadari bisa juga membuat sebuah fungsi/method pada test tersebut sehinga kita bisa menyingkat beberapa code kita. Selain itu, menurut saya jika membuat unit test baru yang memiliki jenis test yang sama dengan test sebelumnya menurut saya kurang efektif. Lebih baik test tersebut ditambahkan ke unit test sebelumnya dibandingkan membuat unit test baru yang mirip. 

</details>

<details open>
<summary>Modul 2</summary>

## Reflection 1

1. Dari merge pull request ke-9 saya mencoba menyelesaikan beberapa issue yang dilaporkan oleh sonarcloud. Terdapat 21 issues yang dilaporkan dan saya melakukan fix hingga tersisa 4 issues saja. Beberapa issue yang terjadi adalah, adanya import yang tidak dibutuhkan, adanya method yang dideklarasikan akan throw exception tetapi tidak ada exceptionnya, dan juga terdapat test case yang tidak memiliki assert. Strategi saya dalam memperbaiki itu tentunya dengan melakukan apa yang disarankan oleh sonarcloud, yaitu menghapus import-import yang tidak dibutuhkan pada suatu file, menghapus throw exception pada method yang tidak memunculkan error apapun, dan juga menambah serta mengedit unit test agar 100% coverage dan memastikan ada assert pada unit test tersebut.
2. Menurut saya sudah walaupun belum melakukan CI/CD yang sempurna. Pada modul ini saya sudah mengimplementasikan CI yaitu penggunaan ci.yml yang akan menjalankan semua unit test (kecuali functional) ketika melakukan push dan pull request ke git. Selain itu saya juga menggunakan sonarcloud untuk keperluan CI saya di mana sonarcloud akan melakukan analisis issue apa saja yang terdapat pada kode-kode saya setiap kali saya melakukan push dan pull request. Akan tetapi, pengecekan otomatis yang dilakukan oleh sonarcloud hanya dilakukan ketika saya melakukan push ke branch main, ini yang membuat saya mengatakan bahwa saya belum melakukan CI yang sempurna. Saya juga sudah mengimplementasikan CD yaitu git akan melakukan deploy ke koyeb setiap kali saya melakukan push ke branch main. Walaupun saya masih menggunakan service yang gratis, tetapi web masih bisa berjalan dengan baik tanpa adanya error.

</details>