
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

<details>
<summary>Modul 2</summary>

## Reflection 1

1. Dari merge pull request ke-9 saya mencoba menyelesaikan beberapa issue yang dilaporkan oleh sonarcloud. Terdapat 21 issues yang dilaporkan dan saya melakukan fix hingga tersisa 4 issues saja. Beberapa issue yang terjadi adalah, adanya import yang tidak dibutuhkan, adanya method yang dideklarasikan akan throw exception tetapi tidak ada exceptionnya, dan juga terdapat test case yang tidak memiliki assert. Strategi saya dalam memperbaiki itu tentunya dengan melakukan apa yang disarankan oleh sonarcloud, yaitu menghapus import-import yang tidak dibutuhkan pada suatu file, menghapus throw exception pada method yang tidak memunculkan error apapun, dan juga menambah serta mengedit unit test agar 100% coverage dan memastikan ada assert pada unit test tersebut.
2. Menurut saya sudah walaupun belum melakukan CI/CD yang sempurna. Pada modul ini saya sudah mengimplementasikan CI yaitu penggunaan ci.yml yang akan menjalankan semua unit test (kecuali functional) ketika melakukan push dan pull request ke git. Selain itu saya juga menggunakan sonarcloud untuk keperluan CI saya di mana sonarcloud akan melakukan analisis issue apa saja yang terdapat pada kode-kode saya setiap kali saya melakukan push dan pull request. Akan tetapi, pengecekan otomatis yang dilakukan oleh sonarcloud hanya dilakukan ketika saya melakukan push ke branch main, ini yang membuat saya mengatakan bahwa saya belum melakukan CI yang sempurna. Saya juga sudah mengimplementasikan CD yaitu git akan melakukan deploy ke koyeb setiap kali saya melakukan push ke branch main. Walaupun saya masih menggunakan service yang gratis, tetapi web masih bisa berjalan dengan baik tanpa adanya error.

</details>

<details open>
<summary>Modul 3</summary>

## Reflection 1

1) Explain what principles you apply to your project!

    1. CarController melanggar SRP karena pada kode awal, CarController berada pada file yang sama dengan ProductController.java padahal keduanya mempunyai tugas yang berbeda. Agar mengikuti SRP, CarController dibuat menjadi file sendiri agar satu file hanya memiliki satu tugas saja.
    2. OCP pada project ini sudah diterapkan di interface CarService dan Product Service. Jika saya ingin menambahkan fungsionalitas baru, saya harus bisa melakukannya tanpa mengubah kode yang sudah ada. Contoh, jika saya ingin menambahkan cara baru untuk mengelola mobil (misalnya, menyimpan ke database alih-alih menggunakan ArrayList), saya cukup membuat implementasi baru tanpa mengubah CarService.
    3. CarController melanggar LSP karena mewarisi ProductController, tetapi memiliki perilaku berbeda (misalnya createCar dan editCar). Agar mengikuti LSP, cukup dengan menghapus extend ProductController pada class CarController
    4. ISP pada project ini sudah diterapkan di interface CarService dan Product Service, di mana kedua interface tidak memaksa class yang mengextendnya (CarServiceImpl & ProductServiceImpl untuk mengimplementasikan metode yang tidak mereka butuhkan.
    5. CarController melanggar DIP karena Controller ini bergantung langsung pada implementasi CarServiceImpl. Agar mengikuti DIP, saya menggunakan interface CarService agar controller hanya bergantung pada abstraksi, bukan implementasi konkret.


2) Explain the advantages of applying SOLID principles to your project with examples.

    1. SRP, keuntungannya adalah setiap kelas hanya memiliki satu tanggung jawab, sehingga lebih mudah untuk dimodifikasi tanpa mempengaruhi bagian lain dari kode. Contohnya sebelum menerapkan SRP: CarController berada dalam file yang sama dengan ProductController.java, padahal keduanya memiliki tugas yang berbeda. Setelah menerapkan SRP: CarController dipisahkan menjadi file sendiri sehingga kode lebih terstruktur dan sesuai dengan tugas masing-masing.
    2. OCP, keuntungannya adalah saya dapat menambahkan fitur baru tanpa mengubah kode yang sudah ada, sehingga lebih stabil dan tidak merusak fungsionalitas yang sudah berjalan. Contohnya sebelum menerapkan OCP: Jika ingin mengubah cara penyimpanan data Car dari ArrayList ke database, saya harus memodifikasi CarServiceImpl, yang dapat menyebabkan bug di tempat lain. Setelah menerapkan OCP: CarService dibuat sebagai interface, sehingga jika ingin menambahkan penyimpanan berbasis database, cukup buat implementasi baru tanpa mengubah kode CarService.
    3. LSP, keuntungannya adalah subclass dapat menggantikan superclass tanpa mengubah perilaku yang diharapkan, sehingga kode lebih stabil dan tidak menyebabkan error tak terduga. Contohnya sebelum menerapkan LSP: CarController mewarisi ProductController, padahal memiliki perilaku yang berbeda. Misalnya, CarController memiliki metode createCar, sedangkan ProductController memiliki createProduct, yang bisa menyebabkan konflik.  Setelah menerapkan LSP: CarController tidak lagi mewarisi ProductController, melainkan berdiri sendiri.
    4. ISP, keuntungannya adalah setiap kelas hanya mengimplementasikan metode yang benar-benar dibutuhkan, sehingga tidak ada metode yang harus diimplementasikan secara paksa meskipun tidak digunakan. Contohnya sebelum menerapkan ISP: Jika CarService memiliki metode yang tidak relevan untuk CarServiceImpl, maka CarServiceImpl tetap harus mengimplementasikannya meskipun tidak digunakan.  Setelah menerapkan ISP: CarService hanya memiliki metode yang benar-benar diperlukan oleh CarServiceImpl, sehingga tidak ada metode yang tidak terpakai.
    5. DIP, keuntungannya adalah kelas tingkat tinggi tidak bergantung langsung pada kelas tingkat rendah, tetapi pada abstraksi, sehingga lebih mudah diganti atau diuji dengan implementasi lain. Contohnya sebelum menerapkan DIP: CarController bergantung langsung pada CarServiceImpl, yang membuatnya sulit untuk diuji atau diganti dengan implementasi lain.  Setelah menerapkan DIP: CarController hanya bergantung pada CarService (interface), sehingga bisa dengan mudah diganti dengan implementasi lain, seperti MockCarService untuk pengujian.


3) Explain the disadvantages of not applying SOLID principles to your project with examples.

    1. Tanpa SRP, masalahnya adalah jika satu file berisi banyak tanggung jawab, setiap perubahan bisa berdampak ke bagian lain yang tidak terkait. Contohnya jika CarController tetap berada di dalam ProductController.java, maka setiap perubahan pada mobil juga bisa memengaruhi produk, yang seharusnya tidak terjadi.
    2. Tanpa OCP, masalahnya adalah jika kode tidak terbuka untuk ekstensi, setiap perubahan harus dilakukan dengan memodifikasi kode yang sudah ada, yang berisiko menimbulkan bug. Contohnya jika CarServiceImpl langsung menggunakan ArrayList, maka saat ingin mengganti penyimpanan ke database, seluruh kode terkait harus dimodifikasi.
    3. Tanpa LSP, masalahnya adalah jika subclass tidak bisa menggantikan superclass dengan benar, maka perilaku sistem bisa menjadi tidak konsisten. Contohnya jika CarController tetap mewarisi ProductController, bisa terjadi error ketika kode mencoba memanggil metode createProduct() dari CarController, yang seharusnya tidak ada.
    4. Tanpa ISP, masalahnya adalah jika interface terlalu besar, maka kelas yang mengimplementasikannya harus menerapkan metode yang tidak dibutuhkan. Contohnya jika CarService memiliki metode calculatePrice(), tetapi CarServiceImpl tidak membutuhkan metode ini, maka CarServiceImpl tetap harus mengimplementasikannya.
    5. Tanpa DIP, masalahnya adalah jika kelas tingkat tinggi bergantung langsung pada kelas tingkat rendah, maka perubahan kecil di satu bagian bisa menyebabkan perubahan besar di tempat lain. Contohnya adalah jika CarController langsung menggunakan CarServiceImpl, maka sulit untuk mengganti CarServiceImpl dengan versi tiruan (mock) saat pengujian.

</details>