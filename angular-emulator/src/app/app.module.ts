import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CoresComponent } from './cores/cores.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CoreDetailComponent } from './core-detail/core-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KeysComponent } from './keys/keys.component';
import { CoreEditorComponent } from './core-editor/core-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    CoresComponent,
    CoreDetailComponent,
    MessagesComponent,
    DashboardComponent,
    KeysComponent,
    CoreEditorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
