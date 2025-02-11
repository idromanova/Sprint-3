package ru.sber.io

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    private val logFile = File("io/logfile.log")
    private val zipFile = File("io/logfile.zip")
    private val unzipFile = File("io/unzippedLogfile.log")

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        ZipOutputStream(zipFile.outputStream()).use { output ->
            FileInputStream(logFile).use { file ->
                val entry = ZipEntry(logFile.name)
                output.putNextEntry(entry)
                file.copyTo(output)
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        FileOutputStream(unzipFile).use { file ->
            ZipInputStream(zipFile.inputStream()).use { input ->
                val entry = input.nextEntry
                if (entry != null) input.copyTo(file)
            }
        }
    }
}