$ErrorActionPreference = "Stop"

function Test-Command($CommandName) {
    return [bool](Get-Command $CommandName -ErrorAction SilentlyContinue)
}

if (-not (Test-Command "java")) {
    Write-Host "Java nao encontrado. Instale o JDK 17 antes de continuar."
    exit 1
}

if (-not (Test-Command "mvn")) {
    Write-Host "Maven nao encontrado. Instale o Maven antes de continuar."
    exit 1
}

$javaVersionOutput = & java -version 2>&1
$javaVersionLine = $javaVersionOutput[0].ToString()

if ($javaVersionLine -notmatch "17\.") {
    Write-Host "Este projeto usa Java 17. Versao atual: $javaVersionLine"
    exit 1
}

if (Test-Path ".env") {
    Get-Content ".env" | ForEach-Object {
        $line = $_.Trim()
        if ($line -and -not $line.StartsWith("#") -and $line.Contains("=")) {
            $parts = $line.Split("=", 2)
            [Environment]::SetEnvironmentVariable($parts[0], $parts[1], "Process")
        }
    }
} else {
    Write-Host "Arquivo .env nao encontrado. Usando valores padrao do application.properties."
    Write-Host "Para Supabase, copie .env.example para .env e preencha as credenciais."
}

mvn spring-boot:run
