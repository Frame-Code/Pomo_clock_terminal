echo "Initializing LibreTranslate"
# Definir variables
$ContainerName = "libretranslate"
$ImageName = "libretranslate/libretranslate:latest"
$Ports = "8600:5000"
$EnvVars = "LT_LOAD_ONLY=en,es"

Write-Host "Verifying if the container '$ContainerName' exists..."

# Verificar si el contenedor existe
# Ejecutamos docker inspect y capturamos su salida, redirigiendo errores a null.
# Luego, comprobamos si el comando tuvo éxito (exit code 0).
$ContainerExists = $false
try {
    $InspectResult = (docker inspect $ContainerName 2>$null)
    if ($LASTEXITCODE -eq 0) {
        $ContainerExists = $true
        $ContainerInfo = $InspectResult | ConvertFrom-Json
    }
} catch {
    # No es necesario hacer nada aquí, $ContainerExists ya es false.
}

if ($ContainerExists) {
    Write-Host "Container '$ContainerName' exists"

    # Verificar si el contenedor está en ejecución
    $IsRunning = $ContainerInfo.State.Running

    if ($IsRunning -eq $true) {
        Write-Host "The container '$ContainerName' is initialized."
    } else {
        Write-Host "Initialazing container '$ContainerName' ..."
        docker start $ContainerName
        Write-Host "Container '$ContainerName' initialized"
    }
} else {
    Write-Host "Installing container '$ContainerName'... "
    docker run -d --name $ContainerName -p $Ports -e $EnvVars $ImageName
    Write-Host "Container '$ContainerName' created and initialized"
}