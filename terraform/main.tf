terraform {
  required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
    }
  }
}

provider "azurerm" {
  features {}
  subscription_id = var.subscription
}

resource "azurerm_resource_group" "project_kube" {
  name     = var.res
  location = "France Central"
}


