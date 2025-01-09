output "ip_address_master" {
  value = azurerm_public_ip.pfe2024_public_ip.ip_address
}
output "ip_address_worker_1" {
  value = azurerm_public_ip.pfe2024_worker_1_public_ip.ip_address
}

output "instance_master_name" {
  value = azurerm_linux_virtual_machine.pfe2024_vm.name
}
output "instance_worker1_name" {
  value = azurerm_linux_virtual_machine.worker2024_vm.name
}