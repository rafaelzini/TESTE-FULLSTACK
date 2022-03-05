import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap/modal';
import { NgxMaskModule } from 'ngx-mask';
import { ToastrModule } from 'ngx-toastr';
import { AppComponent } from './app.component';
import { AppRoutes } from './app.routing';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { CoreModule } from './layouts/core.module';
import { FixedPluginModule } from './shared/fixedplugin/fixedplugin.module';
import { FooterModule } from './shared/footer/footer.module';
import { NavbarModule } from './shared/navbar/navbar.module';
import { SidebarModule } from './sidebar/sidebar.module';

@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
  ],
  imports: [
    CommonModule, 
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    CoreModule,
    NgxMaskModule.forRoot(),
    RouterModule.forRoot(AppRoutes, {
      useHash: true
    }),
    SidebarModule,
    NavbarModule,
    ToastrModule.forRoot(),
    FooterModule,
    FixedPluginModule,
  ],
  providers: [BsModalService],
  bootstrap: [AppComponent]
})
export class AppModule { }
